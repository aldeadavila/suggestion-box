package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    fun findAll(): Flow<Response<List<Comment>>>
    fun findBySuggestion(idSuggestion: String): Flow<Response<List<Comment>>>
    fun findByUser(idUser: String): Flow<Response<List<Comment>>>
    suspend fun createComment(comment: Comment): Response<Boolean>
    suspend fun update(id:String, comment: Comment): Response<Comment>
    suspend fun delete(id: String): Response<Boolean>
}