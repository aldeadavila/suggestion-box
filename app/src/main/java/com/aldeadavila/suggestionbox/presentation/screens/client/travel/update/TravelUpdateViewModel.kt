package com.aldeadavila.suggestionbox.presentation.screens.client.travel.update

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Travel
import com.aldeadavila.suggestionbox.domain.repository.TravelRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TravelUpdateViewModel @Inject constructor(
    private val repository: TravelRepository
): ViewModel() {

    var state by mutableStateOf(TravelUpdateState())
        private set

    var travelType by mutableStateOf("")
        private set

    var travelDirection by mutableStateOf("")
        private set

    var selectedDate by mutableStateOf<Date?>(null)
        private set

    var seats by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set
        
    var paradasText by mutableStateOf("")
        private set

    fun setInitialValues(travel: Travel) {
        travelType = travel.type
        travelDirection = travel.direction
        selectedDate = travel.date
        seats = travel.seats.toString()
        description = travel.description
        paradasText = travel.paradas.joinToString(", ")
    }

    fun onTravelTypeChange(type: String) {
        travelType = type
    }

    fun onTravelDirectionChange(direction: String) {
        travelDirection = direction
    }

    fun onDateSelected(date: Date) {
        selectedDate = date
    }

    fun onSeatsChange(value: String) {
        if (value.isEmpty() || value.toIntOrNull() != null) {
            seats = value
        }
    }

    fun onDescriptionChange(value: String) {
        description = value
    }
    
    fun onParadasChange(value: String) {
        paradasText = value
    }

    fun updateTravel(travel: Travel) {
        if (selectedDate == null) {
            state = state.copy(error = "Selecciona una fecha")
            return
        }

        if (travelType == Travel.OFFER && (seats.isEmpty() || seats.toInt() < 1)) {
            state = state.copy(error = "Indica el número de plazas disponibles")
            return
        }
        
        // Procesar las paradas (separadas por comas)
        val paradasList = paradasText.split(",").map { it.trim() }.filter { it.isNotEmpty() }

        val updatedTravel = Travel(
            id = travel.id,
            userId = travel.userId,
            userName = travel.userName,
            type = travelType,
            direction = travelDirection,
            date = selectedDate,
            seats = if (travelType == Travel.OFFER) seats.toInt() else 0,
            description = description,
            paradas = paradasList,
            createdAt = travel.createdAt,
            updatedAt = Date()
        )

        viewModelScope.launch {
            repository.updateTravel(updatedTravel).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun resetState() {
        state = TravelUpdateState()
    }
}

data class TravelUpdateState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
) 