package com.aldeadavila.suggestionbox.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCategoryScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.ClientSuggestionCreateScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbycategory.ClientSuggestionListByCategoryScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.ClientSuggestionUpdateScreen

fun NavGraphBuilder.ClientCategoryNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_CATEGORY,
        startDestination = ClientCategoryScreen.SuggestionList.route
    ) {

        composable(
            route = ClientCategoryScreen.SuggestionList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("category")?.let {
                ClientSuggestionListByCategoryScreen(
                    navHostController,
                    it
                )
            }
        }

        composable(
            route = ClientCategoryScreen.SuggestionCreate.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("category")?.let {
                ClientSuggestionCreateScreen(navHostController, it)
            }
        }

        composable(
            route = ClientCategoryScreen.SuggestionUpdate.route,
            arguments = listOf(navArgument("suggestion") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("suggestion")?.let {
                ClientSuggestionUpdateScreen(navHostController, it)
            }
        }

    }
}