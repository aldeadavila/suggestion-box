package com.aldeadavila.suggestionbox.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail.CommentDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.ClientCommentUpdateScreen

fun NavGraphBuilder.ClientCommentNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.COMMENT,
        startDestination = ClientCommentScreen.CommentDetail.route
    ) {
        composable(
            route = ClientCommentScreen.CommentDetail.route,
            arguments = listOf(navArgument("comment") {
                type = NavType.StringType
            })
        )  {
            it.arguments?.getString("comment")?.let { comment ->
                CommentDetailScreen(
                    navHostController = navHostController,
                    commentParam = comment
                )
            }
        }

        composable(
            route = ClientCommentScreen.CommentUpdate.route,
            arguments = listOf(navArgument("comment") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("comment")?.let { comment ->
                ClientCommentUpdateScreen(
                    navHostController = navHostController
                )
            }

        }

    }
}

sealed class ClientCommentScreen(val route: String) {
    object CommentDetail: ClientCommentScreen("client/comment/detail/{comment}") {
        fun passComment(comment: String) = "client/comment/detail/$comment"
    }

    object CommentUpdate: ClientCommentScreen("client/comment/update/{comment}") {
        fun passComment(comment: String) = "client/comment/update/$comment"
    }
}

