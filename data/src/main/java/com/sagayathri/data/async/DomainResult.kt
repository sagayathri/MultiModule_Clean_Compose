package com.sagayathri.data.async

import com.sagayathri.data.utils.Mapper
import com.sagayathri.network.utils.ApiResponse

sealed class DomainResult<out T> {
    data class Success<T>(
        val data: T,
    ): DomainResult<T>()

    data class Failure(
        val throwable: Throwable
    ): DomainResult<Nothing>()
}

fun <E, D> ApiResponse<E>.mapDomain(mapper: Mapper<E, D>, error: Throwable) = when (this) {
    is ApiResponse.Success -> mapSuccess(mapper)
    is ApiResponse.Error -> mapError(error)
}

fun <E, D> ApiResponse.Success<E>.mapSuccess(responseMapper: Mapper<E, D>) = DomainResult.Success(
    responseMapper.map(data),
)

fun <E> ApiResponse.Error<E>.mapError(error: Throwable) = DomainResult.Failure( error )