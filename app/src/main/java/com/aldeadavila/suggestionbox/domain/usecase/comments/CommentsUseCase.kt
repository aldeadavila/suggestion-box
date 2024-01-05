package com.aldeadavila.suggestionbox.domain.usecase.comments

data class CommentsUseCase(
    val createCommentUseCase: CreateCommentUseCase,
    val deleteCommentUseCase: DeleteCommentUseCase,
    val findAllCommentsUseCase: FindAllCommentsUseCase,
    val findBySuggestionUseCase: FindBySuggestionUseCase,
    val findByUserUseCase: FindByUserUseCase,
    val updateCommentUseCase: UpdateCommentUseCase,
)
