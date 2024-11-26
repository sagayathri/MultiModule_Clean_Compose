package com.sagayathri.data.repository

import com.sagayathri.core.utils.ApiResponse
import com.sagayathri.network.model.JokesResponse


interface JokesRepository {
    suspend fun getJokes(limit: Int): ApiResponse<JokesResponse>
}