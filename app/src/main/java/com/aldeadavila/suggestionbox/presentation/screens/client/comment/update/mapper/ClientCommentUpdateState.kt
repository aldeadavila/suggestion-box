package com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.mapper

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.ClientCommentUpdateState

fun ClientCommentUpdateState.toComment(): Comment {
    return Comment(
        content = content,
        user_id = user_id
    )
}