package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {

    suspend fun createUser(user: User): Response<Boolean>
    fun getUserById(idUser: String): Flow<User>
    suspend fun update(user: User): Response<Boolean>
    suspend fun  saveImage(file: File, userId: String): Response<String>
}