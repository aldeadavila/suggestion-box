package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.dao.SuggestionsDao
import com.aldeadavila.suggestionbox.data.datasource.local.entity.SuggestionEntity
import kotlinx.coroutines.flow.Flow

class SuggestionsLocalDataSourceImpl(private val suggestionsDao: SuggestionsDao): SuggestionsLocalDataSource {
    override suspend fun create(suggestionEntity: SuggestionEntity) = suggestionsDao.insert(suggestionEntity)

    override suspend fun insertAll(productsEntity: List<SuggestionEntity>) = suggestionsDao.insertAll(productsEntity)

    override fun getSuggestions(): Flow<List<SuggestionEntity>> = suggestionsDao.getSuggestions()
    override fun findByCategory(idCategory: String): Flow<List<SuggestionEntity>> = suggestionsDao.getByCategory(idCategory)

    override suspend fun update(
        id: String,
        name: String,
        description: String,
        image1: String,
        image2: String,

    ) = suggestionsDao.update(id, name, description, image1, image2)

    override suspend fun delete(id: String) = suggestionsDao.delete(id)
}