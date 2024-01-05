package com.aldeadavila.suggestionbox.domain.usecase.comments

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository

class UpdateCommentUseCase(private val repository: CommentsRepository) {
    suspend operator fun invoke(id: String, comment: Comment) = repository.update(id, comment)
}