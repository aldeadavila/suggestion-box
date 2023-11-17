package com.aldeadavila.suggestionbox.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCategoryScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.product.listbycategory.ClientProductListByCategoryScreen

fun NavGraphBuilder.ClientCategoryNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_CATEGORY,
        startDestination = ClientCategoryScreen.ProductList.route
    ) {

        composable(
            route = ClientCategoryScreen.ProductList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("category")?.let {
                ClientProductListByCategoryScreen(
                    navHostController,
                    it
                )
            }
        }

    }
}