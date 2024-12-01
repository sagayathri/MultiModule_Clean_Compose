package com.sagayathri.network.repository.impl

import com.sagayathri.network.api.ApiService
import com.sagayathri.network.model.JokeEntity
import com.sagayathri.network.repository.JokesRepository
import com.sagayathri.network.utils.ApiResponse
import com.sagayathri.network.utils.CoroutineRepository
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : JokesRepository, CoroutineRepository() {

    override suspend fun getJokes(limit: Int): ApiResponse<List<JokeEntity>> {
        return callApi { apiService.getJokes(limit) }
    }

    override suspend fun getJokeByID(jokeId: Int): ApiResponse<JokeEntity> {
        return callApi { apiService.getJokesById(jokeId = jokeId) }
    }
}