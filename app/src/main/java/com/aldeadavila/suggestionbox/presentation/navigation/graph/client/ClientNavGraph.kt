package com.aldeadavila.suggestionbox.presentation.navigation.graph.client

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.graph.profile.ProfileNavGraph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.category.list.ClientCategoryListScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.ClientProductListScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileScreen

@Composable
fun ClientNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        route = Graph.CLIENT,
        startDestination = ClientScreen.SuggestionList.route
    ) {
        composable(route = ClientScreen.SuggestionList.route) {
            ClientProductListScreen(navController)
        }

        composable(route = ClientScreen.CategoryList.route) {
            ClientCategoryListScreen(navController)
        }

        composable(route = ClientScreen.Profile.route) {
            ProfileScreen(navController)
        }

        ProfileNavGraph(navController)

        ClientCategoryNavGraph(navController)

        ClientSuggestionNavGraph(navController)

        ClientCommentNavGraph(navController)
    }
}