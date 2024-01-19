package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

interface CommentsLocalDataSource {

    suspend fun create(commentEntity: CommentEntity)
    suspend fun insertAll(commentEntities: List<CommentEntity>)
    fun getComment(): Flow<List<CommentEntity>>
    fun findBySuggestion(idSuggestion: String): Flow<List<CommentEntity>>
    fun findByUser(idUser: String): Flow<List<CommentEntity>>
    suspend fun update(id: String, content: String)
    suspend fun delete(id: String)
}