package com.aldeadavila.suggestionbox.domain.usecase.suggestions

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import java.io.File

class CreateSuggestionUseCase(private val repository: SuggestionRepository) {

    suspend operator fun  invoke(suggestion: Suggestion, files: List<File>) = repository.createSuggestion(suggestion, files)
}