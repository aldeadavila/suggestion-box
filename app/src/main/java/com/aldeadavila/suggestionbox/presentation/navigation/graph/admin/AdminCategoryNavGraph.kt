package com.aldeadavila.suggestionbox.presentation.navigation.graph.admin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.admin.AdminCategoryScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.update.AdminCategoryUpdateScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.create.AdminCategoryCreateScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.AdminSuggestionCreateScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.list.AdminsuggestionListScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.AdminProductUpdateScreen

fun NavGraphBuilder.AdminCategoryNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.ADMIN_CATEGORY,
        startDestination = AdminCategoryScreen.CategoryCreate.route
    ) {

        composable(route = AdminCategoryScreen.CategoryCreate.route) {
            AdminCategoryCreateScreen(navHostController = navHostController)
        }

        composable(
            route = AdminCategoryScreen.CategoryUpdate.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("category").let {
                AdminCategoryUpdateScreen(navHostController = navHostController)
            }
        }

        composable(
            route = AdminCategoryScreen.ProductList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("category")?.let {
                AdminsuggestionListScreen(navHostController, it)
            }
        }

        composable(
            route = AdminCategoryScreen.SuggestionCreate.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("category")?.let {
                AdminSuggestionCreateScreen(navHostController, it)
            }
        }

        composable(
            route = AdminCategoryScreen.SuggestionUpdate.route,
            arguments = listOf(navArgument("product") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("product")?.let {
                AdminProductUpdateScreen(navHostController, it)
            }
        }

    }
}