package com.aldeadavila.suggestionbox.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
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

        composable(route = DetailsScreen.CreateSuggestion.route) {
            SuggestionCreateScreen(navHostController = navController)
        }

        composable(
            route = DetailsScreen.DetailSuggestion.route,
            arguments = listOf(navArgument("suggestion") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("suggestion")?.let {
                SuggestionDetailScreen(navController, suggestionParam = it)
            }
        }

        composable(
            route = DetailsScreen.UpdateSuggestion.route,
            arguments = listOf(navArgument("suggestion") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("suggestion")?.let {
                ClientSuggestionUpdateScreen(navController, suggestionParam = it)
            }
        }

        CommentNavGraph(navController)

    }

}

sealed class HomeBottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object CategoryList: HomeBottomBarScreen(
        route = "category",
        title = "Categorías",
        icon = Icons.Default.List
    )

    object SuggestionList: HomeBottomBarScreen(
        route = "suggestions",
        title = "Sugerencias",
        icon = Icons.Default.ThumbUp
    )

    object Profile: HomeBottomBarScreen(
        route = "profile",
        title = "Perfíl",
        icon = Icons.Default.Person
    )

}
sealed class DetailsScreen(val route: String) {

    object CreateSuggestion : DetailsScreen("suggestions/create")

    object ProfileUpdate : DetailsScreen("profile/update/{user}") {
        fun passUser(user: String) = "profile/update/$user"
    }

    object DetailSuggestion : DetailsScreen("suggestions/detail/{suggestion}") {
        fun passSuggestion(suggestion: String) = "suggestions/detail/$suggestion"
    }

    object UpdateSuggestion : DetailsScreen("suggestions/update/{suggestion}") {
        fun passSuggestion(suggestion: String) = "suggestions/update/$suggestion"
    }

}