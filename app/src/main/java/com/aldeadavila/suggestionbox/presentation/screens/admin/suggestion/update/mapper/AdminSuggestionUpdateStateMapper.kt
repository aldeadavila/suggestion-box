package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.mapper

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.AdminSuggestionUpdateState

fun AdminSuggestionUpdateState.toSuggestion(): Suggestion {
    return Suggestion(

        name = name,
        description = description,
        idUser = idUser,
        idCategory = idCategory,
        image1 = image1,
        image2 = image2,
        imagesToUpdate = imagesToUpdate.toList(),
    )
}