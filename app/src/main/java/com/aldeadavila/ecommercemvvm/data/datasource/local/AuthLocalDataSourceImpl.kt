package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.datastore.AuthDataStore
import com.aldeadavila.suggestionbox.domain.model.AuthResponse
import com.aldeadavila.suggestionbox.domain.model.User
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl constructor(private val authDataStore: AuthDataStore) :
    AuthLocalDataSource {
    override suspend fun saveSession(authResponse: AuthResponse) =
        authDataStore.saveUser(authResponse)

    override suspend fun updateSession(user: User) = authDataStore.updateUser(user)

    override suspend fun logout() = authDataStore.delete()

    override fun getSessionData(): Flow<AuthResponse> = authDataStore.getData()

}