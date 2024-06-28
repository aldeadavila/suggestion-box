package com.aldeadavila.suggestionbox.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail.CommentDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.ClientCommentUpdateScreen

fun NavGraphBuilder.CommentNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.COMMENT,
        startDestination = CommentScreen.CommentDetail.route
    ) {
        composable(
            route = CommentScreen.CommentDetail.route,
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
            route = CommentScreen.CommentUpdate.route,
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

sealed class CommentScreen(val route: String) {
    object CommentDetail: CommentScreen("comment/detail/{comment}") {
        fun passComment(comment: String) = "comment/detail/$comment"
    }

    object CommentUpdate: CommentScreen("comment/update/{comment}") {
        fun passComment(comment: String) = "comment/update/$comment"
    }
}

