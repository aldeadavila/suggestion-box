package com.aldeadavila.suggestionbox.domain.usecase.comments

import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository

class FindByUserUseCase(private val repository: CommentsRepository) {
    operator fun invoke(idUser: String) = repository.findByUser(idUser)
}