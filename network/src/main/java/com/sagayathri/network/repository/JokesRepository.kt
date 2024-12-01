package com.sagayathri.network.repository

import com.sagayathri.network.model.JokeEntity
import com.sagayathri.network.utils.ApiResponse

interface JokesRepository {
    suspend fun getJokes(limit: Int): ApiResponse<List<JokeEntity>>
    suspend fun getJokeByID(jokeId: Int): ApiResponse<JokeEntity>
}