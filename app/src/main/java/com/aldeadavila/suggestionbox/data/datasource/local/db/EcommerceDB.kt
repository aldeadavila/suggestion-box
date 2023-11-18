package com.aldeadavila.suggestionbox.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aldeadavila.suggestionbox.data.datasource.local.dao.CategoriesDao
import com.aldeadavila.suggestionbox.data.datasource.local.dao.suggestionsDao
import com.aldeadavila.suggestionbox.data.datasource.local.entity.CategoryEntity
import com.aldeadavila.suggestionbox.data.datasource.local.entity.SuggestionEntity

@Database(
    entities = [CategoryEntity::class, SuggestionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EcommerceDB: RoomDatabase() {

    abstract fun categoriesDAO(): CategoriesDao
    abstract fun productsDAO(): suggestionsDao
}