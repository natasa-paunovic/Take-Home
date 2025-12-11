package com.android.take_home.data.repository

import com.android.take_home.data.local.FileCacheManager
import com.android.take_home.domain.util.Resource
import kotlinx.coroutines.flow.*
import com.android.take_home.domain.util.Result

abstract class LocalOfflineRepository<T>(
    private val cache: FileCacheManager<List<T>>
) {

    protected abstract suspend fun fetchRemote(): Result<List<T>>

    fun asFlow(): Flow<Resource<List<T>>> = flow {


        val cached = cache.read()
        if (cached != null) {
            emit(Resource.Success(cached))
        } else {
            emit(Resource.Loading)
        }

        // fetch remote
        when (val result = fetchRemote()) {
            is Result.Success -> {
                cache.write(result.data)
                emit(Resource.Success(result.data))
            }
            is Result.Failure -> {
                emit(Resource.Error(result.throwable, cached))
            }
        }
    }
}