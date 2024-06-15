package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import java.io.File

interface SuggestionRepository {
    suspend fun createSuggestion(suggestion: Suggestion, files: List<File>): Response<Boolean>

}