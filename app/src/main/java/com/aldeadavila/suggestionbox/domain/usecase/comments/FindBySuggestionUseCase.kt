package com.aldeadavila.suggestionbox.domain.usecase.comments

import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository

class FindBySuggestionUseCase(private val repository: CommentsRepository) {
    operator fun invoke(idSuggestion: String) = repository.findBySuggestion(idSuggestion)
}