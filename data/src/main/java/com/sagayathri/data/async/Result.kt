package com.sagayathri.data.async

sealed class Result<out T> {
    data object Loading: Result<Nothing>()

    data class Success<out T>(
        val data: T,
    ): Result<T>()

    data class Failure<T>(
        val throwable: Throwable
    ): Result<T>()
}