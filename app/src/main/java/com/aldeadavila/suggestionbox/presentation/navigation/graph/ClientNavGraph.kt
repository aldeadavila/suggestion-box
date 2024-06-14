package com.aldeadavila.suggestionbox.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.screens.client.category.list.ClientCategoryListScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.ClientSuggestionListScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileScreen

fun NavGraphBuilder.ClientNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CLIENT,
        startDestination = HomeBottomBarScreen.SuggestionList.route
    ) {
        composable(route = HomeBottomBarScreen.SuggestionList.route) {
            ClientSuggestionListScreen(navController)
        }

        composable(route = HomeBottomBarScreen.CategoryList.route) {
            ClientCategoryListScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }


        ClientCategoryNavGraph(navController)

        ClientSuggestionNavGraph(navController)

        ClientCommentNavGraph(navController)
    }
}