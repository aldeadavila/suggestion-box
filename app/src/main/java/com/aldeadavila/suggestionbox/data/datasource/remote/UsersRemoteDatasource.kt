package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.domain.model.User
import retrofit2.Response
import java.io.File

interface UsersRemoteDatasource {

    suspend fun getById(idUser: String): Response<User>
    suspend fun update(id: String, user: User): Response<User>


}