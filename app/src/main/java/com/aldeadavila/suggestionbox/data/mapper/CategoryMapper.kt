package com.aldeadavila.suggestionbox.data.mapper

import com.aldeadavila.suggestionbox.data.datasource.local.entity.CategoryEntity
import com.aldeadavila.suggestionbox.domain.model.Category

fun CategoryEntity.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        description = description,
        image = image
    )
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id ?: "",
        name = name,
        description = description,
        image = image ?: ""
    )
}