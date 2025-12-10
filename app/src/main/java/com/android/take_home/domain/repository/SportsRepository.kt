package com.android.take_home.domain.repository

import com.android.take_home.domain.model.sport.Sport
import com.android.take_home.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface SportsRepository {

    fun fetchSports(): Flow<Resource<List<Sport>>>
}