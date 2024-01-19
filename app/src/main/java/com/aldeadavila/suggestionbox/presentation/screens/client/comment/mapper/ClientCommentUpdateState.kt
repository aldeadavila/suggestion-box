package com.aldeadavila.suggestionbox.presentation.screens.client.comment.mapper

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.ClientCommentUpdateState

fun ClientCommentUpdateState.toComment(): Comment {
    return Comment(
        content = content,
        idUser = idUser,
        idSuggestion = idSuggestion
    )
}