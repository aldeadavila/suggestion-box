package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.dao.CategoriesDao
import com.aldeadavila.suggestionbox.data.datasource.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

class CategoriesLocalDataSourceImpl(private val categoriesDao: CategoriesDao): CategoriesLocalDataSource {
    override suspend fun create(categoryEntity: CategoryEntity) = categoriesDao.insert(categoryEntity)
    override suspend fun insertAll(categoriesEntity: List<CategoryEntity>) = categoriesDao.insertAll(categoriesEntity)

    override fun getCategories(): Flow<List<CategoryEntity>> = categoriesDao.getCategories()

    override suspend fun update(id: String, name: String, description: String, image: String) =
        categoriesDao.update(id, name, description, image)

    override suspend fun delete(id: String)  = categoriesDao.delete(id)
}