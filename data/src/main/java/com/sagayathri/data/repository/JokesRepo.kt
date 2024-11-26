package com.sagayathri.data.repository

import com.sagayathri.data.remote.IApiService
import javax.inject.Inject

class JokesRepo @Inject constructor(
    private val api : IApiService
) {
    suspend fun getJokes(type: String): String {
        var jokes = ""
        return try {
            val response = api.getJokesByType(type)
            if (response.isSuccessful) {
                val responseToJsonSting = response.body()?.string()
                // Convert to parsable JSON format
                jokes = "{ \"jokes\": $responseToJsonSting }"
            }
            jokes
        } catch (exception: Exception) {
            "ERROR $exception"
        }
    }
}