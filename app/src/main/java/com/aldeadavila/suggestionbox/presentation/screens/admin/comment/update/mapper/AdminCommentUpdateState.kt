package com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update.mapper

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update.AdminCommentUpdateState

fun AdminCommentUpdateState.toComment(): Comment {
    return Comment(
        content = content,
        idUser = idUser,
        idSuggestion = idSuggestion
    )
}