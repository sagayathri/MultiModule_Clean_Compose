package com.sagayathri.data.repository.impl

import com.sagayathri.core.di.ApplicationScope
import com.sagayathri.core.di.DefaultDispatcher
import com.sagayathri.data.async.Result
import com.sagayathri.data.mapper.DataMapper
import com.sagayathri.data.model.Joke
import com.sagayathri.data.repository.JokesRepository
import com.sagayathri.network.api.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataMapper: DataMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
): JokesRepository {

    override fun getJokes(limit: Int): Flow<Result<List<Joke>>> = channelFlow {
        send(Result.Loading)
        try {
            apiService.getJokes(limit = limit).run {
                if (this.isSuccessful) {
                    if (this.body() != null) {
                        val mappedData = dataMapper.mapJokeList(this.body()!!)
                        send(
                            Result.Success(
                                data = mappedData
                            )
                        )
                    } else {
                        send(Result.Failure(Throwable("Error!")))
                    }
                } else {
                    send(Result.Failure(Throwable("Error!")))
                }
            }
        } catch (e : Exception){
            send(Result.Failure(e))
        }
    }

    override fun getJokeByID(jokeId: Int): Flow<Result<Joke>> = channelFlow {
        send(Result.Loading)
        try {
            apiService.getJokesById(jokeId = jokeId).run {
                if (this.isSuccessful) {
                    if (this.body() != null) {
                        val mappedData = dataMapper.mapJoke(this.body()!!)
                        send(
                            Result.Success(
                                data = mappedData
                            )
                        )
                    } else {
                        send(Result.Failure(Throwable("Error!")))
                    }
                } else {
                    send(Result.Failure(Throwable("Error!")))
                }
            }
        } catch (e: Exception) {
            send(Result.Failure(e))
        }
    }
}