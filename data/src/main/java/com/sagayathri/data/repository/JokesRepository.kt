package com.sagayathri.data.repository

import com.sagayathri.data.async.AsyncEvents
import com.sagayathri.data.async.Result
import com.sagayathri.data.model.JokesDomain
import kotlinx.coroutines.flow.Flow


interface JokesRepository {
    suspend fun getJokes(limit: Int): Flow<Result<JokesDomain>>
}