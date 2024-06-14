package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.data.datasource.remote.service.UserService
import com.aldeadavila.suggestionbox.domain.model.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class UsersRemoteDatasourceImpl(private val userService: UserService) : UsersRemoteDatasource {
    override suspend fun getById(idUser: String): Response<User> = userService.findById(idUser)

    override suspend fun update(id: String, user: User): Response<User> = userService.update(
        id,
        user
    )


}