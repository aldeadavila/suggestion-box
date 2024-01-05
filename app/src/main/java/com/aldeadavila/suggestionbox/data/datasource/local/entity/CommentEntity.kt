package com.aldeadavila.suggestionbox.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="comments")
data class CommentEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo("id_user")val idUser: String = "",
    @ColumnInfo("id_suggestion")val idSuggestion: String = "",
    @ColumnInfo("content")val content: String = "",
)
