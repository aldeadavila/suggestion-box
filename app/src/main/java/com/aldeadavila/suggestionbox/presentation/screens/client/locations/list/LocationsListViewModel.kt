package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Location
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.usecase.locations.LocationsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsListViewModel @Inject constructor(
    private val locationsUseCases: LocationsUseCases
) : ViewModel() {

    var locationsResponse by mutableStateOf<Response<List<Location>>?>(null)
        private set

    fun getLocations() = viewModelScope.launch {
        locationsResponse = Response.Loading
        locationsUseCases.getLocationsUseCase().collect { response ->
            locationsResponse = response
        }
    }

}