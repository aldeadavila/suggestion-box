package com.aldeadavila.suggestionbox.domain.usecase.suggestions

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository

class UpdateSuggestionUseCase(private val repository: SuggestionRepository) {

    suspend operator fun  invoke(id: String, suggestion: Suggestion) = repository.update(id, suggestion)
}