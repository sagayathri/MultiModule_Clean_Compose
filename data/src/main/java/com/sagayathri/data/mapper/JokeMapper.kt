package com.sagayathri.data.mapper

import com.sagayathri.data.model.Joke
import com.sagayathri.data.utils.Mapper
import com.sagayathri.network.model.JokeEntity
import javax.inject.Inject

class JokeMapper @Inject constructor() : Mapper<JokeEntity, Joke> {

    override fun map(from: JokeEntity): Joke {
        return Joke(
            id = from.id,
            type = from.type,
            setup = from.setup,
            punchline = from.punchline,
        )
    }
}
