package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.data.datasource.remote.UsersRemoteDatasource
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.domain.util.ResponseToRequest
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val usersRef: CollectionReference,
    private val usersRemoteDatasource: UsersRemoteDatasource
    ): UsersRepository {
    override suspend fun createUser(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

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