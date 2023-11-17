package com.aldeadavila.suggestionbox.ui.screens.admin.category.update.mapper

import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.ui.screens.admin.category.update.AdminCategoryUpdateState

fun AdminCategoryUpdateState.toCategory(): Category {
    return Category(
        name = name,
        description = description
    )

}