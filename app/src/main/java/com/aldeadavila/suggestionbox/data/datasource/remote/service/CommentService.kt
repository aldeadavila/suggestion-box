package com.aldeadavila.suggestionbox.data.datasource.remote.service

import com.aldeadavila.suggestionbox.domain.model.Comment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CommentService {

    @GET("comments/suggestion/{id_suggestion}")
    suspend fun findBySuggestion(
        @Path("id_suggestion") idSuggestion: String
    ): Response<List<Comment>>

    @GET("comments/user/{id_user}")
    suspend fun findByUser(
        @Path("id_user") idUser: String
    ): Response<List<Comment>>

    @GET("comments")
    suspend fun findAll(): Response<List<Comment>>

    @PUT("comments/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body comment: Comment
    ): Response<Comment>

    @POST("comments")
    suspend fun create(
        @Body() comment: Comment
    ): Response<Comment>

    @DELETE("comments/{id}")
    suspend fun delete(
        @Path("id") id: String,
    ): Response<Unit>
}