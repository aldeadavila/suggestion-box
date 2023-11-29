package com.aldeadavila.suggestionbox.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aldeadavila.suggestionbox.data.datasource.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categoriesEntity: List<CategoryEntity>)
    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("UPDATE categories SET name = :name, description = :description, image = :image WHERE id = :id")
    suspend fun update(id:String, name:String, description:String, image:String)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun delete(id:String)


}