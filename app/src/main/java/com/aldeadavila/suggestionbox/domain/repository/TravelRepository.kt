package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Travel
import kotlinx.coroutines.flow.Flow
import com.aldeadavila.suggestionbox.domain.util.Resource

interface TravelRepository {
    fun getTravels(): Flow<Resource<List<Travel>>>
    fun getTravelsByUser(userId: String): Flow<Resource<List<Travel>>>
    fun createTravel(travel: Travel): Flow<Resource<Travel>>
    fun updateTravel(travel: Travel): Flow<Resource<Travel>>
    fun deleteTravel(id: String): Flow<Resource<String>>
} 