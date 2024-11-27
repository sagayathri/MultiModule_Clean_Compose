package com.sagayathri.presentation.home

import com.sagayathri.data.model.JokeDomain

data class JokesUIState(
    val items: List<JokeDomain> = emptyList(),
    val isLoading: Boolean = false,
)