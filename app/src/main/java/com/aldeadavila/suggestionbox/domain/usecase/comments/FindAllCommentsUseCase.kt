package com.aldeadavila.suggestionbox.domain.usecase.comments

import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository

class FindAllCommentsUseCase(private val repository: CommentsRepository) {
    operator fun invoke() = repository.findAll()
}