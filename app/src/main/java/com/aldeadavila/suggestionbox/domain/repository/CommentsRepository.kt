package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    fun findAll(): Flow<Resource<List<Comment>>>
    fun findBySuggestion(idSuggestion: String): Flow<Resource<List<Comment>>>
    fun findByUser(idUser: String): Flow<Resource<List<Comment>>>
    suspend fun create(comment: Comment): Resource<Comment>
    suspend fun update(id:String, comment: Comment): Resource<Comment>
    suspend fun delete(id: String): Resource<Unit>
}