package com.android.take_home.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import java.io.File

class FileCacheManager<T>(
    private val file: File,
    private val serializer: KSerializer<T>
) {
    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    suspend fun read(): T? = withContext(Dispatchers.IO) {
        if (!file.exists()) return@withContext null
        try {
            val text = file.readText()
            json.decodeFromString(serializer, text)
        } catch (e: Throwable) {
            null
        }
    }

    suspend fun write(value: T) = withContext(Dispatchers.IO) {
        val tmp = File(file.parentFile, "${file.name}.tmp")
        tmp.writeText(json.encodeToString(serializer, value))
        tmp.renameTo(file)
    }
}