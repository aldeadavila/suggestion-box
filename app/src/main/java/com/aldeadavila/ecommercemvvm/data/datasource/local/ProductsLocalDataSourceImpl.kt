package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.dao.ProductsDao
import com.aldeadavila.suggestionbox.data.datasource.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductsLocalDataSourceImpl(private val productsDao: ProductsDao): ProductsLocalDataSource {
    override suspend fun create(productEntity: ProductEntity) = productsDao.insert(productEntity)

    override suspend fun insertAll(productsEntity: List<ProductEntity>) = productsDao.insertAll(productsEntity)

    override fun getProducts(): Flow<List<ProductEntity>> = productsDao.getProducts()
    override fun findByCategory(idCategory: String): Flow<List<ProductEntity>> = productsDao.getByCategory(idCategory)

    override suspend fun update(
        id: String,
        name: String,
        description: String,
        image1: String,
        image2: String,
        price: Double
    ) = productsDao.update(id, name, description, image1, image2, price)

    override suspend fun delete(id: String) = productsDao.delete(id)
}