package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File


interface CategoriesRepository {

    suspend fun create(category: Category, file: File): Response<Category>
    fun getCategories(): Flow<Response<List<Category>>>
    suspend fun update(id: String, category: Category): Response<Category>
    suspend fun updateWithImage(id:String,category: Category, file: File): Response<Category>
    suspend fun delete(id:String): Response<Unit>
}