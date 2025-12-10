package com.android.take_home.domain.util

sealed class Resource<out T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Error(
        val throwable: Throwable,
        val cached: Any? = null
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}