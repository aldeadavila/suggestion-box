package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {

    suspend fun createUser(user: User): Response<Boolean>
    fun getUserById(idUser: String): Flow<User>
    suspend fun update(id: String, user: User): Resource<User>
    suspend fun updateWithImage(id: String, user: User, file: File): Resource<User>
}