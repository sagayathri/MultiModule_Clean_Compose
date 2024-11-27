package com.sagayathri.data.repository.impl

import com.sagayathri.core.di.ApplicationScope
import com.sagayathri.core.di.DefaultDispatcher
import com.sagayathri.data.async.Result
import com.sagayathri.data.model.JokesDomain
import com.sagayathri.data.repository.JokesRepository
import com.sagayathri.network.api.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
): JokesRepository {

    override suspend fun getJokes(limit: Int): Flow<Result<JokesDomain>> = flow {
        emit(Result.Loading)
        scope.launch {
            withContext(defaultDispatcher) {
                try {
                    apiService.getJokes(limit = limit).run {
                        if (this.isSuccessful) {
                            if (this.body() != null) {
                                emit(
                                    Result.Success(
                                        data = this.body()!!.jokes as JokesDomain
                                    )
                                )
                            } else {
                                emit(Result.Failure(Throwable("Error!")))
                            }
                        } else {
                            emit(Result.Failure(Throwable("Error!")))
                        }
                    }
                } catch (e: Exception) {
                    emit(Result.Failure(e))
                }
            }
        }
    }
}