package com.android.take_home.data.repository

import com.android.take_home.data.local.FileCacheManager
import com.android.take_home.data.remote.RetrofitApiService
import com.android.take_home.domain.model.sport.Sport
import com.android.take_home.domain.repository.SportsRepository
import com.android.take_home.domain.util.Resource
import com.android.take_home.domain.util.Result
import kotlinx.coroutines.flow.Flow

class SportsRepositoryImpl(
    private val api: RetrofitApiService,
    cache: FileCacheManager<List<Sport>>
) : LocalOfflineRepository<Sport>(cache), SportsRepository {

    override suspend fun fetchRemote(): Result<List<Sport>> =
        try {
            val response = api.getSports()
            if (response.isSuccessful) {
                Result.Success(response.body().orEmpty())
            } else {
                Result.Failure(
                    Throwable("Sports API error ${response.code()}")
                )
            }
        } catch (e: Throwable) {
            Result.Failure(e)
        }


    override fun fetchSports(): Flow<Resource<List<Sport>>> = asFlow()


}