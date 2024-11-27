package com.sagayathri.data.repository

import com.sagayathri.data.async.Result
import com.sagayathri.data.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    fun getJokes(limit: Int): Flow<Result<List<Joke>>>
    fun getJokeByID(jokeId: Int): Flow<Result<Joke>>
}