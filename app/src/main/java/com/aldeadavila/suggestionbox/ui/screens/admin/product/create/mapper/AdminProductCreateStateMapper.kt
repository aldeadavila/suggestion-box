package com.aldeadavila.suggestionbox.ui.screens.admin.product.create.mapper

import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.ui.screens.admin.product.create.AdminProductCreateState

fun AdminProductCreateState.toProduct(): Product {
    return Product(
        name = name,
        description = description,
        idCategory = idCategory,
        price = price
    )
}