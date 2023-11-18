package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.mapper

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.AdminProductUpdateState

fun AdminProductUpdateState.toProduct(): Suggestion {
    return Suggestion(

        name = name,
        description = description,
        idCategory = idCategory,
        price = price,
        imagesToUpdate = imagesToUpdate.toList(),
        image1 = image1,
        image2 = image2
    )
}