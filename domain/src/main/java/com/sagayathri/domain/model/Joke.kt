package com.sagayathri.domain.model

import com.sagayathri.core.model.DomainModel

data class Joke (
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
) : DomainModel