package com.aldeadavila.suggestionbox.domain.usecase.locations

import com.aldeadavila.suggestionbox.domain.repository.LocationsRepository
import javax.inject.Inject

class GetLocationsUsecase @Inject constructor(private val repository: LocationsRepository) {
    operator fun invoke() = repository.getSuggestions()
}