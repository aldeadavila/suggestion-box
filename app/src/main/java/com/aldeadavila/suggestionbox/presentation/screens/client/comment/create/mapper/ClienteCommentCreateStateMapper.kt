package com.aldeadavila.suggestionbox.presentation.screens.client.comment.create.mapper

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.create.ClientCommentCreateState

fun ClientCommentCreateState.toComment(): Comment {
    return Comment(
        content = content,
        idUser = idUser,
        idSuggestion = idSuggestion
    )
}