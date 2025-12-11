package com.android.take_home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.take_home.presentation.MatchesViewModel
import org.koin.androidx.compose.koinViewModel

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import com.android.take_home.presentation.ui.match.live.LiveMatchesSection
import com.android.take_home.presentation.ui.match.upcoming.UpcomingMatchesSection
import com.android.take_home.presentation.ui.sports.SportsTabRow


@Composable
fun MainScreen(
    viewModel: MatchesViewModel = koinViewModel()
) {
    val sports by viewModel.sports.collectAsState()
    val liveMatches by viewModel.liveMatches.collectAsState()
    val upcomingMatches by viewModel.upcomingMatches.collectAsState()
    val selectedSportId by viewModel.selectedSportId.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121516))
            .padding(
                top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),      // padding top za status bar
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() // padding bottom za bottom bar
            ),

        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 12.dp)
    ) {

        // SPORT TABS
        item {
            if (sports.isNotEmpty()) {
                SportsTabRow(
                    sports = sports,
                    selectedSportId = selectedSportId,
                    onSportSelected = viewModel::selectSport
                )
            }
        }

        // LIVE MATCHES
        if (liveMatches.isNotEmpty()) {
            item {
                LiveMatchesSection(liveMatches)
            }
        }

        item {
            UpcomingMatchesSection(
                matches = upcomingMatches,
                selectedTab = selectedTab,
                onTabSelected = viewModel::selectTab
            )
        }
    }
}


