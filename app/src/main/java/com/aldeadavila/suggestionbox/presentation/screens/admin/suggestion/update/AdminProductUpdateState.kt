package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update

data class AdminProductUpdateState(

    val id: String = "",
    val name: String = "",
    val description: String = "",
    val idCategory: String = "",
    val image1: String = "",
    val image2: String = "",
    val idUser: String = "",
    val imagesToUpdate: MutableList<Int> = mutableListOf()
)
