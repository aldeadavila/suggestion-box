package com.aldeadavila.suggestionbox.data.mapper

import com.aldeadavila.suggestionbox.data.datasource.local.entity.CommentEntity
import com.aldeadavila.suggestionbox.domain.model.Comment

fun CommentEntity.toComment(): Comment {
    return Comment(
        id = id,
        content = content,
        idUser = idUser,
        idSuggestion = idSuggestion
    )
}

fun Comment.toCommentEntity(): CommentEntity {
    return CommentEntity(
        id = id ?: "",
        content = content,
        idUser = idUser,
        idSuggestion = idSuggestion
    )
}