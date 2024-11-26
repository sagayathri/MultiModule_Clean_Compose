package com.sagayathri.domain.model

import com.sagayathri.core.model.DomainModel

data class Jokes(
    val jokes: List<Joke>? = null
) : DomainModel