package com.sagayathri.core.utils

import java.io.IOException

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val error: Exception) : ApiResponse<Nothing>()
}