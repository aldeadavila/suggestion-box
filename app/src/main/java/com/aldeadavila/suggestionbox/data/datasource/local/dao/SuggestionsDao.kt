package com.aldeadavila.suggestionbox.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aldeadavila.suggestionbox.data.datasource.local.entity.SuggestionEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SuggestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(suggestionEntity: SuggestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categoriesEntity: List<SuggestionEntity>)
    @Query("SELECT * FROM suggestions")
    fun getSuggestions(): Flow<List<SuggestionEntity>>

    @Query("SELECT * FROM suggestions WHERE id_category = :id_category")
    fun getByCategory(id_category:String): Flow<List<SuggestionEntity>>

    @Query("UPDATE suggestions SET name = :name, description = :description, image1 = :image1, image2 = :image2 WHERE id = :id")
    suspend fun update(id:String, name:String, description:String, image1:String, image2:String)

    @Query("DELETE FROM suggestions WHERE id = :id")
    suspend fun delete(id:String)


}