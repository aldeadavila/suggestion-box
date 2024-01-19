package com.aldeadavila.suggestionbox.domain.usecase.comments

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository

class CreateCommentUseCase(private val repository: CommentsRepository) {
    suspend operator fun invoke(comment: Comment) = repository.create(comment)
}