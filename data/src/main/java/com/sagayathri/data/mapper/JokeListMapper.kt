package com.sagayathri.data.mapper

import com.sagayathri.data.model.Joke
import com.sagayathri.data.utils.Mapper
import com.sagayathri.network.model.JokeEntity
import javax.inject.Inject
import kotlin.collections.map

class JokeListMapper @Inject constructor() : Mapper<List<JokeEntity>, List<Joke>> {

    override fun map(from: List<JokeEntity>): List<Joke> {
        return from.map { mapJoke(it) }
    }

    fun mapJoke(from: JokeEntity): Joke {
        return Joke(
            id = from.id,
            type = from.type,
            setup = from.setup,
            punchline = from.punchline,
        )
    }
}

