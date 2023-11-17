package com.aldeadavila.suggestionbox.domain.usecase.products

import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository

class FindAllUseCase(private val repository: ProductsRepository) {

    operator fun invoke() = repository.findAll()
}