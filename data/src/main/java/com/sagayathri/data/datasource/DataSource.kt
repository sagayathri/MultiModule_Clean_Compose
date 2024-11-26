package com.sagayathri.data.datasource

import com.sagayathri.network.model.JokesResponse
import retrofit2.Response

interface DataSource {
    suspend fun getJokes(limit: Int): Response<JokesResponse>
}