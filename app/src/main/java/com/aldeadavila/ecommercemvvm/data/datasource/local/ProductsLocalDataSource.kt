package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductsLocalDataSource {

    suspend fun create(productEntity: ProductEntity)

    suspend fun insertAll(productsEntity:List<ProductEntity>)
    fun getProducts(): Flow<List<ProductEntity>>
    fun findByCategory(idCategory:String): Flow<List<ProductEntity>>
    suspend fun update(id:String, name:String, description:String, image1:String, image2:String, price:Double)
    suspend fun delete(id:String)
}