package com.aldeadavila.suggestionbox.domain.usecase.products

import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository

class UpdateProductUseCase(private val repository: ProductsRepository) {

    suspend operator fun  invoke(id: String, product: Product) = repository.update(id, product)
}