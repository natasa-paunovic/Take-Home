package com.android.take_home.domain.repository


import com.android.take_home.domain.model.match.Match
import com.android.take_home.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    fun fetchMatches(): Flow<Resource<List<Match>>>
}
