package com.sagayathri.network.utils

import retrofit2.Response

abstract class CoroutineRepository() {
    suspend fun <T> callApi(runBlock: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = runBlock()
            val responseBody = response.body()
            when {
                response.isSuccessful && responseBody != null -> ApiResponse.Success(responseBody)
                else -> ApiResponse.Error(Throwable())
            }
        } catch (e: Throwable) {
            ApiResponse.Error(Throwable())
        }
    }
}