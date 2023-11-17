package com.aldeadavila.suggestionbox.domain.usecase.products

import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository

class FindByCategoryUseCase(private val repository: ProductsRepository) {

    suspend operator fun invoke(idCategory: String) = repository.findByCategory(idCategory)
}