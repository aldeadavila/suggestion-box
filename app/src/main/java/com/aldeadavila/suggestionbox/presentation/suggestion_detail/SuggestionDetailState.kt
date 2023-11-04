package com.aldeadavila.suggestionbox.presentation.suggestion_detail

import com.aldeadavila.suggestionbox.domain.model.Suggestion

data class SuggestionDetailState(
    val isLoading: Boolean = false,
    val suggestion: Suggestion? = null,
    val error: String = ""
)
