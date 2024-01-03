package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.mapper

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.ClientSuggestionCreateState

fun ClientSuggestionCreateState.toProduct(): Suggestion {
    return Suggestion(
        name = name,
        description = description,
        idCategory = idCategory,
        idUser = idUser
    )
}