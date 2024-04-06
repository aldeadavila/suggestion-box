package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.data.datasource.remote.UsersRemoteDatasource
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.domain.util.ResponseToRequest
import java.io.File


class UsersRepositoryImpl(
    private val usersRemoteDatasource: UsersRemoteDatasource
    ): UsersRepository {
    override suspend fun findById(idUser: String): Resource<User> = ResponseToRequest.send(
        usersRemoteDatasource.getById(idUser)
    )

    override suspend fun update(id: String, user: User): Resource<User> = ResponseToRequest.send(
        usersRemoteDatasource.update(id, user)
    )

    override suspend fun updateWithImage(id: String, user: User, file: File): Resource<User> = ResponseToRequest.send(
        usersRemoteDatasource.updateWithImage(id, user, file)
    )

}