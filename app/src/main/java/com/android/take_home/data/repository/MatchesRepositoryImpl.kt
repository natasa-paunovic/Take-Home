package com.android.take_home.data.repository

import com.android.take_home.data.local.FileCacheManager
import com.android.take_home.data.remote.RetrofitApiService
import com.android.take_home.domain.model.match.Match
import com.android.take_home.domain.repository.MatchesRepository
import com.android.take_home.domain.util.Resource
import com.android.take_home.domain.util.Result
import kotlinx.coroutines.flow.Flow

class MatchesRepositoryImpl(
    private val api: RetrofitApiService,
    cache: FileCacheManager<List<Match>>
) : LocalOfflineRepository<Match>(cache), MatchesRepository {

    override suspend fun fetchRemote(): Result<List<Match>> =
        try {
            val data = api.getMatches()
            Result.Success(data)
        } catch (e: Throwable) {
            Result.Failure(e)
        }


    override fun fetchMatches(): Flow<Resource<List<Match>>> = asFlow()

}