package com.aldeadavila.suggestionbox.presentation.screens.admin.comment.create

data class AdminCommentCreateState(
    val id: String = "",
    var content: String = "",
    val idUser: String = "",
    val idSuggestion: String = "",
)
