package com.sagayathri.network.api

import com.sagayathri.network.model.JokeEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/jokes/random/{limit}")
    suspend fun getJokes(
        @Path("limit") limit: Int
    ): Response<List<JokeEntity>>

    @GET("/jokes/{id}")
    suspend fun getJokesById(
        @Path("id") jokeId: Int
    ): Response<JokeEntity>
}