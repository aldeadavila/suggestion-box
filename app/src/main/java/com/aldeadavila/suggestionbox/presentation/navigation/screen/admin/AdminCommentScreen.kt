package com.aldeadavila.suggestionbox.presentation.navigation.screen.admin

import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCommentScreen

sealed class AdminCommentScreen (val route: String) {
    object CommentDetail: AdminCommentScreen("admin/comment/detail/{comment}") {
        fun passComment(comment: String) = "admin/comment/detail/$comment"
    }

    object CommentUpdate: AdminCommentScreen("admin/comment/update/{comment}") {
        fun passComment(comment: String) = "admin/comment/update/$comment"
    }
}