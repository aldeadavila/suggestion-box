package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update

data class AdminSuggestionUpdateState(

    val id: String = "",
    val name: String = "",
    val description: String = "",
    val idUser: String = "",
    val idCategory: String = "",
    val image1: String = "",
    val image2: String = "",
    val imagesToUpdate: MutableList<Int> = mutableListOf()
)
