package com.sagayathri.presentation.jokeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagayathri.data.async.Result
import com.sagayathri.data.repository.JokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeDetailsViewModel @Inject constructor(
    private val repository: JokesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        getInitialState(),
    )
    val state: StateFlow<JokeItemUIState> =
        _state.asStateFlow()

    private fun getInitialState() = JokeItemUIState(
        item = null,
        isLoading = false,
    )

    fun fetchJokeByID(jokeId: Int){
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            repository.getJokeByID(jokeId = jokeId).collect{ result ->
                when(result){
                    is Result.Success ->_state.value = _state.value.copy(item = result.data, isLoading = false)
                    else -> _state.value = _state.value.copy(item = null, isLoading = false)
                }
            }
        }
    }
}