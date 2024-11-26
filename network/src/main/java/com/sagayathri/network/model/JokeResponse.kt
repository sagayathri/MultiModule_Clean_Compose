package com.sagayathri.network.model

import com.sagayathri.core.model.ResponseModel
import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse (
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
): ResponseModel