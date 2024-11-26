package com.sagayathri.data.model

import com.sagayathri.core.model.ResponseModel

data class JokeResponse (
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
) : ResponseModel