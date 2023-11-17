package com.aldeadavila.suggestionbox.domain.usecase.products

import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository
import java.io.File

class UpdateProductWithImageUseCase(private val repository: ProductsRepository) {

    suspend operator fun  invoke(id: String, product: Product, files: List<File>) = repository.updateWithImage(id, product, files)
}