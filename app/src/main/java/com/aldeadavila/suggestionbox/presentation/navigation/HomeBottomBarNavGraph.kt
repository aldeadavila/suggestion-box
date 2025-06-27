package com.aldeadavila.suggestionbox.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Flight
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.LocationsListScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.news.list.NewsScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.SuggestionListScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.update.ProfileUpdateScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.TravelScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.create.TravelCreateScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.detail.TravelDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.update.TravelUpdateScreen

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

        composable(route = HomeBottomBarScreen.Travel.route) {
            TravelScreen(navController)
        }

        composable(route = "travel/create") {
            TravelCreateScreen(navController)
        }

        composable(
            route = "travel/detail/{travel}",
            arguments = listOf(navArgument("travel") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("travel")?.let { travel ->
                TravelDetailScreen(navController, travelParam = travel)
            }
        }

        composable(
            route = "travel/update/{travel}",
            arguments = listOf(navArgument("travel") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("travel")?.let { travel ->
                TravelUpdateScreen(navController, travelParam = travel)
            }
        }

        composable(route = HomeBottomBarScreen.Map.route) {
            LocationsListScreen(navController)
        }

        composable(route = HomeBottomBarScreen.NewsList.route) {
            NewsScreen(navController)
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
    object NewsList : HomeBottomBarScreen(
        route = "news",
        title = "Noticias",
        icon = Icons.AutoMirrored.Filled.List
    )

    object SuggestionList : HomeBottomBarScreen(
        route = "suggestions",
        title = "Sugerencias",
        icon = Icons.Default.ThumbUp
    )

    object Travel : HomeBottomBarScreen(
        route = "travel",
        title = "Viajar",
        icon = Icons.Default.Flight
    )

    object Profile : HomeBottomBarScreen(
        route = "profile",
        title = "Perfíl",
        icon = Icons.Default.Person
    )

    object Map : HomeBottomBarScreen(
        route = "map",
        title = "Mapa",
        icon = Icons.Default.Map
    )
}

