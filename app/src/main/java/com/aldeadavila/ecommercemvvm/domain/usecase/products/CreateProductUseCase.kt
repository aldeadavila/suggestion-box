package com.aldeadavila.suggestionbox.domain.usecase.products

import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository
import java.io.File

class CreateProductUseCase(private val repository: ProductsRepository) {

    suspend operator fun  invoke(product: Product, files: List<File>) = repository.create(product, files)
}