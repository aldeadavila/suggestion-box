package com.aldeadavila.suggestionbox.domain.usecase.suggestions

import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository

class FindByCategoryUseCase(private val repository: SuggestionRepository) {

    operator fun invoke(idCategory: String) = repository.findByCategory(idCategory)
}