package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Location
import com.aldeadavila.suggestionbox.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {

    fun getLocations(): Flow<Response<List<Location>>>
}