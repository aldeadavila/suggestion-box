package com.aldeadavila.suggestionbox.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aldeadavila.suggestionbox.data.datasource.local.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commentEntity: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(commentEntity: List<CommentEntity>)

    @Query("SELECT * FROM comments")
    fun getComments(): Flow<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE id_suggestion = :id_suggestion")
    fun getBySuggestion(id_suggestion:String): Flow<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE id_user = :id_user")
    fun getByUser(id_user:String): Flow<List<CommentEntity>>

    @Query("UPDATE comments SET content = :content WHERE id = :id")
    suspend fun update(id:String, content:String)

    @Query("DELETE FROM comments WHERE id = :id")
    suspend fun delete(id:String)
}