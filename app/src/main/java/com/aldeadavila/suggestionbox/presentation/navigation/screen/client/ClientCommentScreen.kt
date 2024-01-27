package com.aldeadavila.suggestionbox.presentation.navigation.screen.client

sealed class ClientCommentScreen(val route: String) {
    object CommentDetail: ClientCommentScreen("client/comment/detail/{comment}") {
        fun passComment(comment: String) = "client/comment/detail/$comment"
    }

    object CommentUpdate: ClientCommentScreen("client/comment/update/{comment}") {
        fun passComment(comment: String) = "client/comment/update/$comment"
    }
}