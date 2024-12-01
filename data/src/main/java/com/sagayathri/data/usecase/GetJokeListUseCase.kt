package com.sagayathri.data.usecase

import com.sagayathri.data.async.mapDomain
import com.sagayathri.data.async.Result
import com.sagayathri.data.mapper.JokeListMapper
import com.sagayathri.data.model.Joke
import com.sagayathri.network.repository.JokesRepository
import javax.inject.Inject

class GetJokeListUseCase @Inject constructor(
    private val repository: JokesRepository,
    private val dataMapper: JokeListMapper,
) {
    suspend operator fun invoke(limit: Int): Result<List<Joke>> {
        return repository.getJokes(limit = limit)
            .mapDomain(dataMapper, Throwable()) // TODO - Implement error mapper
    }
}