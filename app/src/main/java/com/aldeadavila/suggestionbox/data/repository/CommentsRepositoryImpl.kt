package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(): CommentsRepository {
    override fun findAll(): Flow<Resource<List<Comment>>> {
        TODO("Not yet implemented")
    }

    override fun findBySuggestion(idSuggestion: String): Flow<Resource<List<Comment>>> {
        TODO("Not yet implemented")
    }

    override fun findByUser(idUser: String): Flow<Resource<List<Comment>>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(comment: Comment): Resource<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, comment: Comment): Resource<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Resource<Unit> {
        TODO("Not yet implemented")
    }
}