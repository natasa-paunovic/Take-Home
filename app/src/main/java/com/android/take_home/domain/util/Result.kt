package com.android.take_home.domain.util

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val throwable: Throwable) : Result<Nothing>()
}