package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.domain.model.AuthResponse
import com.aldeadavila.suggestionbox.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {

    suspend fun saveSession(authResponse: AuthResponse)
    suspend fun updateSession(user: User)
    suspend fun logout()
    fun getSessionData(): Flow<AuthResponse>
}