package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.dao.CommentsDao
import com.aldeadavila.suggestionbox.data.datasource.local.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

class CommentsLocalDataSourceImpl(private val commentsDao: CommentsDao): CommentsLocalDataSource {
    override suspend fun create(commentEntity: CommentEntity) = commentsDao.insert(commentEntity)
    override suspend fun insertAll(commentEntities: List<CommentEntity>) = commentsDao.insertAll(commentEntities)
    override fun getComment(): Flow<List<CommentEntity>> = commentsDao.getComments()
    override fun findBySuggestion(idSuggestion: String): Flow<List<CommentEntity>> = commentsDao.getBySuggestion(idSuggestion)
    override fun findByUser(idUser: String): Flow<List<CommentEntity>> = commentsDao.getByUser(idUser)
    override suspend fun update(id: String, content: String) = commentsDao.update(id, content)
    override suspend fun delete(id: String) = commentsDao.delete(id)
}