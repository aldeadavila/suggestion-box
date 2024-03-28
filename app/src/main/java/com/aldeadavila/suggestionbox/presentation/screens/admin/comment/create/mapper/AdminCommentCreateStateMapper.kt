package com.aldeadavila.suggestionbox.presentation.screens.admin.comment.create.mapper

import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.create.AdminCommentCreateState

fun AdminCommentCreateState.toComment(): Comment {
    return Comment(
        content = content,
        idUser = idUser,
        idSuggestion = idSuggestion
    )
}