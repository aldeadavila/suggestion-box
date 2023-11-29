package com.aldeadavila.suggestionbox.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="suggestions")
data class SuggestionEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo("name")val name: String = "",
    @ColumnInfo("description")val description: String = "",
    @ColumnInfo("id_user")val idUser: String = "",
    @ColumnInfo("id_category")val idCategory: String = "",
    @ColumnInfo("image1")val image1: String = "",
    @ColumnInfo("image2")val image2: String = "",


    )
