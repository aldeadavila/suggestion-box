package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Suggestion

interface SuggestionRepository {

    fun addNewSuggestion(suggestion: Suggestion)
}