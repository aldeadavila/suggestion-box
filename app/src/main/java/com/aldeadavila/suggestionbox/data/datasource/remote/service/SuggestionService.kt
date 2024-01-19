package com.aldeadavila.suggestionbox.data.datasource.remote.service


import com.aldeadavila.suggestionbox.domain.model.Suggestion
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface SuggestionService {

    @GET("suggestions/category/{id_category}")
    suspend fun findByCategory(
        @Path("id_category") idCategory: String
    ): Response<List<Suggestion>>

    @GET("suggestions")
    suspend fun findAll(): Response<List<Suggestion>>

    @Multipart
    @POST("suggestions")
    suspend fun create(
        @Part files: Array<MultipartBody.Part?>,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("id_category") idCategory: RequestBody,
        @Part("id_user") idUser: RequestBody,
    ): Response<Suggestion>

    @Multipart
    @PUT("suggestions/upload/{id}")
    suspend fun updateWithImage(
        @Part files: Array<MultipartBody.Part?>,
        @Path("id") id:String,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("id_category") idCategory: RequestBody,
        @Part("id_user") idUser: RequestBody,
        @Part("images_to_update[]") imagesToUpdate: Array<RequestBody?>,
    ): Response<Suggestion>

    @PUT("suggestions/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body suggestion: Suggestion
    ): Response<Suggestion>

    @DELETE("suggestions/{id}")
    suspend fun delete(
        @Path("id") id: String,
    ): Response<Unit>


}