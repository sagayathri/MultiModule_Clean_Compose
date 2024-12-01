package com.sagayathri.data.usecase

import com.sagayathri.data.async.DomainResult
import com.sagayathri.data.async.mapDomain
import com.sagayathri.data.mapper.JokeMapper
import com.sagayathri.data.model.Joke
import com.sagayathri.network.repository.JokesRepository
import javax.inject.Inject

class GetJokeByIdUseCase @Inject constructor(
    private val repository: JokesRepository,
    private val dataMapper: JokeMapper,
) {

    suspend operator fun invoke(jokeId: Int): DomainResult<Joke> {
        return repository.getJokeByID(jokeId = jokeId)
            .mapDomain(dataMapper, Throwable()) // TODO - Implement error mapper
    }
}