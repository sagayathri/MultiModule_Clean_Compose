package com.sagayathri.network.utils

sealed class ApiResponse<E> {
    data class Success<E>(val data: E) : ApiResponse<E>()
    data class Error<E>(val error: Throwable) : ApiResponse<E>()
}