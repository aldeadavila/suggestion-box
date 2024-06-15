package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update

data class ClientSuggestionUpdateState(

    val id: String = "",
    val name: String = "",
    val description: String = "",
    val idUser: String = "",
    val category: String = "",
    val image1: String = "",
    val image2: String = "",
    val imagesToUpdate: MutableList<Int> = mutableListOf()

)
