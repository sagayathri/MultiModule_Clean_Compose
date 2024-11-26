package com.sagayathri.network.model

import kotlinx.serialization.Serializable

@Serializable
data class JokesEntity(
    val jokes: List<JokeEntity>? = null
)