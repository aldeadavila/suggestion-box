package com.aldeadavila.suggestionbox.domain.usecase.comments

import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository

class DeleteCommentUseCase(private val repository: CommentsRepository) {
    suspend operator fun invoke(id: String) = repository.delete(id)
}