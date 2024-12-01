package com.sagayathri.presentation.jokeDetail

import androidx.lifecycle.ViewModel
import com.sagayathri.data.async.DomainResult
import com.sagayathri.data.usecase.GetJokeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JokeDetailsViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeByIdUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(
        getInitialState(),
    )
    val state: StateFlow<JokeItemUIState> =
        _state.asStateFlow()

    private fun getInitialState() = JokeItemUIState(
        item = null,
        isLoading = false,
        isLoaded = false,
    )

    suspend fun fetchJokeByID(jokeId: Int) {
        _state.value = _state.value.copy(isLoading = true)

        _state.value =  when (
            val result = getJokeUseCase(jokeId = jokeId)
        ) {
            is DomainResult.Success -> {
                _state.value.copy(item = result.data, isLoading = false, isLoaded = true)
            }

            else -> {
                _state.value.copy(item = null, isLoading = false, isLoaded = true)
            }
        }
    }
}