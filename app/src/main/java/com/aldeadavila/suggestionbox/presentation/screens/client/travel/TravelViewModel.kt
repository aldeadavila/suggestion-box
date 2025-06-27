package com.aldeadavila.suggestionbox.presentation.screens.client.travel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Travel
import com.aldeadavila.suggestionbox.domain.repository.TravelRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TravelViewModel @Inject constructor(
    private val repository: TravelRepository
) : ViewModel() {

    var state by mutableStateOf(TravelState())
        private set

    var userTravelsState by mutableStateOf(TravelState())
        private set

    init {
        getTravels()
    }

    fun getTravels() {
        viewModelScope.launch {
            repository.getTravels().collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            travels = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getTravelsByUser(userId: String) {
        viewModelScope.launch {
            repository.getTravelsByUser(userId).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        userTravelsState = userTravelsState.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        userTravelsState = userTravelsState.copy(
                            travels = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        userTravelsState = userTravelsState.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun createTravel(travel: Travel) {
        viewModelScope.launch {
            repository.createTravel(travel).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(isLoading = false)
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun updateTravel(travel: Travel) {
        viewModelScope.launch {
            repository.updateTravel(travel).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(isLoading = false)
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun deleteTravel(id: String) {
        viewModelScope.launch {
            repository.deleteTravel(id).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(isLoading = false)
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}

data class TravelState(
    val travels: List<Travel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) 