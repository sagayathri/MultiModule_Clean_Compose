package com.sagayathri.data.mapper

import com.sagayathri.data.model.Joke
import com.sagayathri.network.model.JokeEntity
import javax.inject.Inject
import kotlin.collections.map

class DataMapper @Inject constructor() {
    fun JokeEntity.toModel() = Joke(id, type, setup, punchline)

    fun mapJoke(from: JokeEntity): Joke {
        return Joke(
            id = from.id,
            type = from.type,
            setup = from.setup,
            punchline = from.punchline,
        )
    }

    fun mapJokeList(from: List<JokeEntity>): List<Joke> {
        return from.map {
            it.toModel()
        }
    }
}

