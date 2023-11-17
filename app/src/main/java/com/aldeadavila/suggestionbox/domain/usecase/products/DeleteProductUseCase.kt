package com.aldeadavila.suggestionbox.domain.usecase.products

import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository

class DeleteProductUseCase(private val repository: ProductsRepository) {

    suspend operator fun invoke(id: String) = repository.detele(id)
}