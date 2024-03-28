package com.aldeadavila.suggestionbox.presentation.navigation.graph.admin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.admin.AdminSuggestionScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.detail.AdminSuggestionDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.AdminSuggestionUpdateScreen

fun NavGraphBuilder.AdminSuggestionNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.ADMIN_SUGGESTION,
        startDestination = AdminSuggestionScreen.SuggestionDetail.route
    ) {

        composable(
            route = AdminSuggestionScreen.SuggestionDetail.route,
            arguments = listOf(navArgument("suggestion") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("suggestion")?.let {
                AdminSuggestionDetailScreen(
                    navHostController,
                    it
                )
            }
        }

        composable(
            route = AdminSuggestionScreen.SuggestionUpdate.route,
            arguments = listOf(navArgument("suggestion") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("suggestion")?.let {
                AdminSuggestionUpdateScreen(
                    navHostController,
                    it
                )
            }
        }
    }
}