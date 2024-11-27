package com.sagayathri.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagayathri.data.async.AsyncEvents
import com.sagayathri.data.async.Result
import com.sagayathri.data.repository.JokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesListViewModel @Inject constructor(
    private val repository: JokesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        getInitialState(),
    )
    val state: StateFlow<JokesUIState> =
        _state.asStateFlow()

    init {
        fetchJokes(10) //Number of jokes to be listed
    }

    private fun getInitialState() = JokesUIState(
        isLoading = false,
        items = emptyList(),
    )

    fun fetchJokes(limit: Int){
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            repository.getJokes(limit = limit).collect{ result ->
                when(result){
                    is Result.Success ->_state.value = _state.value.copy(result.data.jokes, isLoading = false)
                    else -> _state.value = _state.value.copy(items = emptyList(), isLoading = false)
                }
            }
        }
    }
}