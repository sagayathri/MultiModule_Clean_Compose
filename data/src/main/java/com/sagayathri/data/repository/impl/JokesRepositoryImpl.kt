package com.sagayathri.data.repository.impl

import com.sagayathri.core.utils.ApiResponse
import com.sagayathri.data.repository.JokesRepository
import com.sagayathri.data.utils.CoroutineRepository
import com.sagayathri.network.api.ApiService
import com.sagayathri.network.model.JokesEntity
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): JokesRepository, CoroutineRepository() {
    override suspend fun getJokes(limit: Int): ApiResponse<JokesEntity> {
        return handleAPICall {
            apiService.getJokes(limit = limit)
        }
    }
}