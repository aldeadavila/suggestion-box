package com.aldeadavila.suggestionbox.data.datasource.local

import com.aldeadavila.suggestionbox.data.datasource.local.entity.SuggestionEntity
import kotlinx.coroutines.flow.Flow

interface SuggestionsLocalDataSource {

    suspend fun create(suggestionEntity: SuggestionEntity)

    suspend fun insertAll(productsEntity:List<SuggestionEntity>)
    fun getSuggestions(): Flow<List<SuggestionEntity>>
    fun findByCategory(idCategory:String): Flow<List<SuggestionEntity>>
    suspend fun update(id:String, name:String, description:String, image1:String, image2:String, price:Double)
    suspend fun delete(id:String)
}