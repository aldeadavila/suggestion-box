package com.aldeadavila.suggestionbox.ui.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.ui.navigation.Graph
import com.aldeadavila.suggestionbox.ui.navigation.screen.client.ClientProductScreen
import com.aldeadavila.suggestionbox.ui.screens.client.product.detail.ClientProductDetailScreen

fun NavGraphBuilder.ClientProudctNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_PRODUCT,
        startDestination = ClientProductScreen.ProductDetail.route
    ) {

        composable(
            route = ClientProductScreen.ProductDetail.route,
            arguments = listOf(navArgument("product") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("product")?.let {
                ClientProductDetailScreen(
                    navHostController,
                    it
                )
            }
        }
    }
}