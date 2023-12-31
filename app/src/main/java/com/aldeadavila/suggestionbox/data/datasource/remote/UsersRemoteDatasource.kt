package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.domain.model.User
import retrofit2.Response
import java.io.File

interface UsersRemoteDatasource {
    suspend fun update(id: String, user: User): Response<User>

    suspend fun updateWithImage(id: String, user: User, file: File): Response<User>


}