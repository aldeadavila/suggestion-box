package com.aldeadavila.suggestionbox.domain.usecase.suggestions

import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import javax.inject.Inject

class getSuggestions @Inject constructor(private val repository: SuggestionRepository) {

    operator fun invoke() = repository.getSuggestions()
}