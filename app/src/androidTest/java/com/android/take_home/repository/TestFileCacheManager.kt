package com.android.take_home.repository

import com.android.take_home.data.local.FileCacheManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestFileCacheManager<T>(var cached: T? = null)  {

    suspend fun read(): T? = withContext(Dispatchers.IO) { cached }

    suspend fun write(value: T) = withContext(Dispatchers.IO) { cached = value }
}