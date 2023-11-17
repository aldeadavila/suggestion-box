package com.aldeadavila.suggestionbox.ui.screens.admin.product.update.mapper

import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.ui.screens.admin.product.update.AdminProductUpdateState

fun AdminProductUpdateState.toProduct(): Product {
    return Product(

        name = name,
        description = description,
        idCategory = idCategory,
        price = price,
        imagesToUpdate = imagesToUpdate.toList(),
        image1 = image1,
        image2 = image2
    )
}