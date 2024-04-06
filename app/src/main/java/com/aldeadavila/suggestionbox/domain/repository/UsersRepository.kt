package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.util.Resource
import java.io.File

interface UsersRepository {
    suspend fun findById(idUser: String): Resource<User>
    suspend fun update(id: String, user: User): Resource<User>
    suspend fun updateWithImage(id: String, user: User, file: File): Resource<User>
}