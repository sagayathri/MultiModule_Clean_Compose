package com.sagayathri.presentation.jokeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagayathri.data.async.Result
import com.sagayathri.data.usecase.GetJokeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesListViewModel @Inject constructor(
    private val getJokesUseCase: GetJokeListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        getInitialState(),
    )
    val state: StateFlow<JokesUIState> =
        _state.asStateFlow()

    private fun getInitialState() = JokesUIState(
        isLoading = false,
        items = emptyList(),
        isLoaded = false,
    )

    suspend fun fetchJokes(limit: Int) {
        _state.value = _state.value.copy(isLoading = true)

        _state.value = when (
            val result = getJokesUseCase(limit = limit)
        ) {
            is Result.Success -> {
                _state.value.copy(items = result.data, isLoading = false, isLoaded = true)
            }

            else -> {
                _state.value.copy(items = emptyList(), isLoading = false, isLoaded = true)
            }
        }
    }
}