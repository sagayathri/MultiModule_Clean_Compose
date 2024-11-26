package com.sagayathri.data.model

import com.sagayathri.core.model.ResponseModel

data class JokesResponse(
    val jokes: List<JokeResponse>? = null
) : ResponseModel