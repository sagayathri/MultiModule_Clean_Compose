package com.sagayathri.presentation.jokeDetail

import com.sagayathri.data.model.Joke

data class JokeItemUIState(
    val item: Joke? = null,
    val isLoading: Boolean = false,
)