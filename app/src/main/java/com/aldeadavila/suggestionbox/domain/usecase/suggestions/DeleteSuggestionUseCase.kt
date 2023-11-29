package com.aldeadavila.suggestionbox.domain.usecase.suggestions

import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository

class DeleteSuggestionUseCase(private val repository: SuggestionRepository) {

    suspend operator fun invoke(id: String) = repository.detele(id)
}