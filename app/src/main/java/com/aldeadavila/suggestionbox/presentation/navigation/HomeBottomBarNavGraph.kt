package com.aldeadavila.suggestionbox.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.SuggestionCreateScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.SuggestionListScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.ClientSuggestionUpdateScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.update.ProfileUpdateScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.SuggestionList.route
    ) {

        composable(route = HomeBottomBarScreen.SuggestionList.route) {
            SuggestionListScreen(navController)
        }

        composable(route = HomeBottomBarScreen.CategoryList.route) {
            //    ClientCategoryListScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(
            route = DetailsScreen.ProfileUpdate.route,
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("user")?.let {
                ProfileUpdateScreen(navController, userParam = it)
            }
        }


        detailsNavGraph(navController)
        CommentNavGraph(navController)

    }

}

sealed class HomeBottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object CategoryList : HomeBottomBarScreen(
        route = "category",
        title = "Categorías",
        icon = Icons.AutoMirrored.Filled.List
    )

    object SuggestionList : HomeBottomBarScreen(
        route = "suggestions",
        title = "Sugerencias",
        icon = Icons.Default.ThumbUp
    )

    object Profile : HomeBottomBarScreen(
        route = "profile",
        title = "Perfíl",
        icon = Icons.Default.Person
    )

}

