package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ProductsRepository {

    fun findAll(): Flow<Resource<List<Product>>>
    fun findByCategory(idCategory: String): Flow<Resource<List<Product>>>
    suspend fun create(product: Product, files: List<File>): Resource<Product>
    suspend fun updateWithImage(id:String, product: Product, files: List<File>?): Resource<Product>
    suspend fun update(id:String, product: Product): Resource<Product>
    suspend fun detele(id: String): Resource<Unit>
}