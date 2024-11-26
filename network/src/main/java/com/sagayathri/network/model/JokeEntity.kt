package com.sagayathri.network.model

import kotlinx.serialization.Serializable

@Serializable
data class JokeEntity (
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
)