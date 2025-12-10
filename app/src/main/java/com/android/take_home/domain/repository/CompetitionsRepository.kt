package com.android.take_home.domain.repository

import com.android.take_home.domain.model.competition.Competition
import com.android.take_home.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CompetitionsRepository {
    fun fetchCompetitions(): Flow<Resource<List<Competition>>>
}