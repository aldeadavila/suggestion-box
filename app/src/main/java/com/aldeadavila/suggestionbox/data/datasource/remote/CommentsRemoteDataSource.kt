package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import retrofit2.Response
import java.io.File

interface CommentsRemoteDataSource {

    suspend fun findAll(): Response<List<Comment>>
    suspend fun findBySuggestion(idSuggestion: String): Response<List<Comment>>
    suspend fun findByUser(idUser: String): Response<List<Comment>>
    suspend fun create(comment: Comment): Response<Comment>
    suspend fun update(id: String, comment: Comment): Response<Comment>
    suspend fun detele(id: String): Response<Unit>
}