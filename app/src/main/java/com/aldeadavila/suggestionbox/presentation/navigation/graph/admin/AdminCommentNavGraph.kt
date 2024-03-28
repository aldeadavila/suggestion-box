package com.aldeadavila.suggestionbox.presentation.navigation.graph.admin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.admin.AdminCommentScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.detail.AdminCommentDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update.AdminCommentUpdateScreen

fun NavGraphBuilder.AdminCommentNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.ADMIN_COMMENT,
        startDestination = AdminCommentScreen.CommentDetail.route
    ) {
        composable(
            route = AdminCommentScreen.CommentDetail.route,
            arguments = listOf(navArgument("comment") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("comment")?.let { comment ->
                AdminCommentDetailScreen(
                    navHostController = navHostController,
                    commentParam = comment
                )
            }
        }

        composable(
            route = AdminCommentScreen.CommentUpdate.route,
            arguments = listOf(navArgument("comment") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("comment")?.let { comment ->
                AdminCommentUpdateScreen(
                    navHostController = navHostController
                )
            }

        }

    }
}