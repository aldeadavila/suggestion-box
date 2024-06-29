package com.aldeadavila.suggestionbox.domain.usecase.suggestions;

import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository;

import javax.inject.Inject;

class DeleteSuggestionUseCase @Inject constructor(private val repository:SuggestionRepository) {
    suspend operator fun invoke(idSuggestion: String) = repository.delete(idSuggestion)
}
