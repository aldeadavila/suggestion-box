package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.domain.model.AuthResponse
import com.aldeadavila.suggestionbox.domain.model.User
import retrofit2.Response

interface AuthRemoteDatasource {

    suspend fun login(email:String, password:String): Response<AuthResponse>
    suspend fun register(user: User): Response<AuthResponse>

}