package com.aldeadavila.suggestionbox.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.screens.client.news.details.NewsDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.SuggestionCreateScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.ClientSuggestionUpdateScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileUpdate.route
    ) {
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

        composable(
            route = DetailsScreen.DetailNews.route,
            arguments = listOf(navArgument("news") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("news")?.let {
                NewsDetailScreen(navController, newsParam = it)
            }
        }

    }
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

    object DetailNews : DetailsScreen("news/detail/{news}") {
        fun passNews(news: String) = "news/detail/$news"
    }

}