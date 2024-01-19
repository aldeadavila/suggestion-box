package com.aldeadavila.suggestionbox.presentation.screens.client.comment

data class ClientCommentCreateState(
    val id: String = "",
    var content: String = "",
    val idUser: String = "",
    val idSuggestion: String = "",
)
