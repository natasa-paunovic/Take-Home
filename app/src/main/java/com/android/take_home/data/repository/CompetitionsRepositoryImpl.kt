package com.android.take_home.data.repository

import com.android.take_home.data.local.FileCacheManager
import com.android.take_home.data.remote.RetrofitApiService
import com.android.take_home.domain.model.competition.Competition
import com.android.take_home.domain.repository.CompetitionsRepository
import com.android.take_home.domain.util.Resource
import com.android.take_home.domain.util.Result
import kotlinx.coroutines.flow.Flow

class CompetitionsRepositoryImpl(
    private val api: RetrofitApiService,
    cache: FileCacheManager<List<Competition>>
) : LocalOfflineRepository<Competition>(cache), CompetitionsRepository {

    override suspend fun fetchRemote(): Result<List<Competition>> =
        try {
            val data = api.getCompetitions()
            Result.Success(data)
        } catch (e: Throwable) {
            Result.Failure(e)
        }

    override fun fetchCompetitions(): Flow<Resource<List<Competition>>> = asFlow()


}