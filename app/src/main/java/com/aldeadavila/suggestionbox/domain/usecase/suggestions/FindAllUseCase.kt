package com.aldeadavila.suggestionbox.domain.usecase.suggestions

import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository

class FindAllUseCase(private val repository: SuggestionRepository) {

    operator fun invoke() = repository.findAll()
}