package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.mapper

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.AdminSuggestionCreateState

fun AdminSuggestionCreateState.toProduct(): Suggestion {
    return Suggestion(
        name = name,
        description = description,
        idCategory = idCategory,
        idUser = idUser
    )
}