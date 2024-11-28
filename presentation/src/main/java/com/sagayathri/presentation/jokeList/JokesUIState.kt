package com.sagayathri.presentation.jokeList

import com.sagayathri.data.model.Joke

data class JokesUIState(
    val items: List<Joke> = emptyList(),
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
)