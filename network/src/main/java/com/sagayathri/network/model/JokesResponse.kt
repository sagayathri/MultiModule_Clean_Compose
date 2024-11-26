package com.sagayathri.network.model

import com.sagayathri.core.model.ResponseModel
import kotlinx.serialization.Serializable

@Serializable
data class JokesResponse(
    val jokes: List<JokeResponse>? = null
) : ResponseModel