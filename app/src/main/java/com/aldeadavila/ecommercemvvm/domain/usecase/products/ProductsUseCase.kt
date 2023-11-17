package com.aldeadavila.suggestionbox.domain.usecase.products

data class ProductsUseCase(
    val createProduct: CreateProductUseCase,
    val findByCategory: FindByCategoryUseCase,
    val findAll : FindAllUseCase,
    val updateProduct: UpdateProductUseCase,
    val updateProductWithImage: UpdateProductWithImageUseCase,
    val deleteProduct: DeleteProductUseCase,

)
