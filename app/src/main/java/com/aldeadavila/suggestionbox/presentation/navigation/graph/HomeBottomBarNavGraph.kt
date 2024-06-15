package com.aldeadavila.suggestionbox.presentation.navigation.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.ClientSuggestionListScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.SuggestionList.route
    ) {

        composable(route = HomeBottomBarScreen.SuggestionList.route) {
            ClientSuggestionListScreen(navController)
        }

        composable(route = HomeBottomBarScreen.CategoryList.route) {
        //    ClientCategoryListScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }

       DetailsNavGraph(navController)

    }

}

sealed class HomeBottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object CategoryList: HomeBottomBarScreen(
        route = "client/category/list",
        title = "Categorías",
        icon = Icons.Default.List
    )

    object SuggestionList: HomeBottomBarScreen(
        route = "client/suggestion/list",
        title = "Sugerencias",
        icon = Icons.Default.ThumbUp
    )

    object Profile: HomeBottomBarScreen(
        route = "client/profile",
        title = "Perfíl",
        icon = Icons.Default.Person
    )

}