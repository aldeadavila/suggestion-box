package com.aldeadavila.suggestionbox.data.mapper

import com.aldeadavila.suggestionbox.data.datasource.local.entity.SuggestionEntity
import com.aldeadavila.suggestionbox.domain.model.Suggestion

fun SuggestionEntity.toProduct(): Suggestion {
    return Suggestion(
        id = id,
        name = name,
        description = description,
        image1 = image1,
        image2 = image2,
        price = price,
        idCategory = idCategory,
        imagesToUpdate = null
    )
}

fun Suggestion.toProductEntity(): SuggestionEntity {
    return SuggestionEntity(
        id = id ?: "",
        name = name,
        description = description,
        image1 = image1 ?: "",
        image2 = image2 ?: "",
        price = price,
        idCategory = idCategory
    )
}