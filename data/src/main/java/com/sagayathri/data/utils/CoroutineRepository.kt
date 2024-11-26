package com.sagayathri.data.utils

import com.sagayathri.core.model.ResponseModel
import com.sagayathri.core.utils.ApiResponse
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

abstract class CoroutineRepository {
    suspend fun <T: ResponseModel> handleAPICall(
        runBlock: suspend () -> Response<T>
    ): ApiResponse<T> {
        return try {
            runBlock.invoke().handleAPIResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse.Error(e)
        }
    }

    private fun <T : ResponseModel> Response<T>.handleAPIResponse(): ApiResponse<T> {
        val responseBody = body() as T
        if (isSuccess()) {
            return ApiResponse.Success(responseBody)
        }
        return ApiResponse.Error(
            IOException()
        )
    }

    private fun <T : ResponseModel> Response<T>.isSuccess(): Boolean = isSuccessful
}