package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.data.datasource.remote.service.CommentService
import com.aldeadavila.suggestionbox.domain.model.Comment
import retrofit2.Response

class CommentsRemoteDataSourceImpl(private val commentService: CommentService) : CommentsRemoteDataSource {
    override suspend fun findAll(): Response<List<Comment>> = commentService.findAll()

    override suspend fun findBySuggestion(idSuggestion: String): Response<List<Comment>> =
        commentService.findBySuggestion(idSuggestion)

    override suspend fun findByUser(idUser: String): Response<List<Comment>> = commentService.findByUser(idUser)

    override suspend fun create(comment: Comment): Response<Comment> = commentService.create(comment)

    override suspend fun update(id: String, comment: Comment): Response<Comment> = commentService.update(id, comment)

    override suspend fun detele(id: String): Response<Unit> = commentService.delete(id)
}