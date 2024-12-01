package com.sagayathri.data.async

import com.sagayathri.data.utils.Mapper
import com.sagayathri.network.utils.ApiResponse

sealed class Result<out T> {
    data class Success<T>(
        val data: T,
    ): Result<T>()

    data class Failure(
        val throwable: Throwable
    ): Result<Nothing>()
}

fun <E, D> ApiResponse<E>.mapDomain(mapper: Mapper<E, D>, error: Throwable) = when (this) {
    is ApiResponse.Success -> mapSuccess(mapper)
    is ApiResponse.Error -> mapError(error)
}

fun <E, D> ApiResponse.Success<E>.mapSuccess(responseMapper: Mapper<E, D>) = Result.Success(
    responseMapper.map(data),
)

fun <E> ApiResponse.Error<E>.mapError(error: Throwable) = Result.Failure( error )