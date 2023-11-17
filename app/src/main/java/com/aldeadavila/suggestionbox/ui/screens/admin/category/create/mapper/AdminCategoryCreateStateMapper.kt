package com.aldeadavila.suggestionbox.ui.screens.admin.category.create.mapper

import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.ui.screens.admin.category.create.AdminCategoryCreateState

fun AdminCategoryCreateState.toCategory(): Category {
    return Category(
        name = name,
        description = description
    )

}