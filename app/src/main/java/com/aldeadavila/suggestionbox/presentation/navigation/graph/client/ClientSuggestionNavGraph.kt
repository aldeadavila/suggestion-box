package com.aldeadavila.suggestionbox.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientSuggestionScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.ClientSuggestionDetailScreen

fun NavGraphBuilder.ClientSuggestionNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_SUGGESTION,
        startDestination = ClientSuggestionScreen.SuggestionDetail.route
    ) {

        composable(
            route = ClientSuggestionScreen.SuggestionDetail.route,
            arguments = listOf(navArgument("suggestion") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("suggestion")?.let {
                ClientSuggestionDetailScreen(
                    navHostController,
                    it
                )
            }
        }
    }
}