package com.aldeadavila.suggestionbox.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCommentScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail.ClientCommentDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.ClientCommentUpdateScreen

fun NavGraphBuilder.ClientCommentNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_COMMENT,
        startDestination = ClientCommentScreen.CommentDetail.route
    ) {
        composable(
            route = ClientCommentScreen.CommentDetail.route,
            arguments = listOf(navArgument("comment") {
                type = NavType.StringType
            })
        )  {
            it.arguments?.getString("comment")?.let { comment ->
                ClientCommentDetailScreen(
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