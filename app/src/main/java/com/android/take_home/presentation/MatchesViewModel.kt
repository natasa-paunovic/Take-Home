package com.android.take_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.take_home.domain.model.competition.Competition
import com.android.take_home.domain.model.match.Match
import com.android.take_home.domain.model.sport.Sport
import com.android.take_home.domain.repository.CompetitionsRepository
import com.android.take_home.domain.repository.MatchesRepository
import com.android.take_home.domain.repository.SportsRepository
import com.android.take_home.domain.util.Resource
import com.android.take_home.presentation.mapper.TAB_TODAY
import com.android.take_home.presentation.mapper.toLiveMatchUI
import com.android.take_home.presentation.mapper.toTab
import com.android.take_home.presentation.mapper.toUpcomingMatchUI
import com.android.take_home.presentation.model.LiveMatchUI
import com.android.take_home.presentation.model.UpcomingMatchUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlin.collections.List

class MatchesViewModel(
    private val matchesRepository: MatchesRepository,
    private val competitionsRepository: CompetitionsRepository,
    private val sportsRepository: SportsRepository
) : ViewModel() {

    // Sports
    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports: StateFlow<List<Sport>> = _sports

    // Selected tab (Today, Tomorrow, Weekend, Next 7 Days)
    private val _selectedTab = MutableStateFlow(TAB_TODAY)
    val selectedTab: StateFlow<String> = _selectedTab

    // Selected sport
    private val _selectedSportId = MutableStateFlow<Int>(0)
    val selectedSportId: StateFlow<Int> = _selectedSportId

    // Cache of all upcoming matches (before tab filtering)
    private val _allUpcomingMatches = MutableStateFlow<List<UpcomingMatchUI>>(emptyList())

    // Live matches
    private val _liveMatches = MutableStateFlow<List<LiveMatchUI>>(emptyList())
    val liveMatches: StateFlow<List<LiveMatchUI>> = _liveMatches

    // Upcoming matches (filtered by tab)
    private val _upcomingMatches = MutableStateFlow<List<UpcomingMatchUI>>(emptyList())
    val upcomingMatches: StateFlow<List<UpcomingMatchUI>> = _upcomingMatches

    // Loading states
    private val _isLoadingMatches = MutableStateFlow(false)
    val isLoadingMatches: StateFlow<Boolean> = _isLoadingMatches

    private val _isLoadingCompetitions = MutableStateFlow(false)
    val isLoadingCompetitions: StateFlow<Boolean> = _isLoadingCompetitions

    // Error states
    private val _matchesError = MutableStateFlow<String?>(null)
    val matchesError: StateFlow<String?> = _matchesError

    // Cache of all matches and competitions
    private var cachedMatches: List<Match> = emptyList()
    private var cachedCompetitionsMap: Map<Int, Competition> = emptyMap()

    init {
        // Fetch sports
        viewModelScope.launch {
            sportsRepository.fetchSports().collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _sports.value = resource.data
                        if (_selectedSportId.value == 0 && resource.data.isNotEmpty()) {
                            _selectedSportId.value = resource.data.first().id
                        }
                    }
                    is Resource.Loading -> { }
                    is Resource.Error -> { }
                }
            }
        }

        // Fetch and process matches and competitions
        viewModelScope.launch {
            combine(
                matchesRepository.fetchMatches(),
                competitionsRepository.fetchCompetitions(),
                selectedSportId
            ) { matchesRes, competitionsRes, sportId ->

                // Handle loading states
                _isLoadingMatches.value = matchesRes is Resource.Loading
                _isLoadingCompetitions.value = competitionsRes is Resource.Loading

                // Handle errors
                when {
                    matchesRes is Resource.Error -> {
                        _matchesError.value = "Error"
                    }
                    competitionsRes is Resource.Error -> {
                        _matchesError.value = "Error"
                    }
                    else -> {
                        _matchesError.value = null
                    }
                }

                // Update cache when we get successful data
                when (competitionsRes) {
                    is Resource.Success -> {
                        cachedCompetitionsMap = competitionsRes.data.associateBy { it.id }
                    }
                    else -> {  }
                }

                when (matchesRes) {
                    is Resource.Success -> {
                        cachedMatches = matchesRes.data
                    }
                    else -> {  }
                }

                // Return cached data with current sport ID
                Triple(cachedMatches, cachedCompetitionsMap, sportId)
            }
                .collect { (matches, competitionsMap, sportId) ->
                    // Filter and update matches
                    updateMatchesForSport(matches, competitionsMap, sportId)
                }
        }

        // Listen to tab changes and re-filter by tab only
        viewModelScope.launch {
            selectedTab.collect { tab ->
                // Re-filter by tab from all upcoming matches
                _upcomingMatches.value = _allUpcomingMatches.value.filter { it.tab == tab }
            }
        }
    }

    private fun updateMatchesForSport(
        matches: List<Match>,
        competitionsMap: Map<Int, Competition>,
        sportId: Int
    ) {
        // Filter by sport and valid competition
        val filteredMatches = if (sportId != 0) {
            matches.filter { match ->
                val competition = competitionsMap[match.competitionId]

                // Match must have a valid competition and both match and competition belong to the selected sport
                competition != null &&
                        match.sportId == sportId &&
                        competition.sportId == sportId
            }
        } else {
            matches
        }

        // Live matches
        _liveMatches.value = filteredMatches.mapNotNull { it.toLiveMatchUI(competitionsMap) }

        // All upcoming matches (with tab set)
        val allUpcoming = filteredMatches.mapNotNull { match ->
            match.toUpcomingMatchUI(competitionsMap)?.copy(
                tab = match.date.toTab()
            )
        }

        _allUpcomingMatches.value = allUpcoming

        // Filter by current selected tab
        _upcomingMatches.value = allUpcoming.filter { it.tab == _selectedTab.value }
    }

    // When tab changes
    fun selectTab(tab: String) {
        _selectedTab.value = tab
        // The selectedTab flow collector will handle the filtering
    }

    // When sport changes
    fun selectSport(sportId: Int) {
        _selectedTab.value = TAB_TODAY
        _selectedSportId.value = sportId
        // The combine flow will handle the re-filtering
    }

    // Helper function to retry fetching
    fun retry() {
        _matchesError.value = null
        // Trigger refresh by reassigning the sport
        val currentSport = _selectedSportId.value
        _selectedSportId.value = 0
        _selectedSportId.value = currentSport
    }
}