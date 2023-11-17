package com.aldeadavila.suggestionbox.ui.navigation.graph.client

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldeadavila.suggestionbox.ui.navigation.Graph
import com.aldeadavila.suggestionbox.ui.navigation.graph.profile.ProfileNavGraph
import com.aldeadavila.suggestionbox.ui.navigation.screen.client.ClientScreen
import com.aldeadavila.suggestionbox.ui.screens.client.category.list.ClientCategoryListScreen
import com.aldeadavila.suggestionbox.ui.screens.client.product.list.ClientProductListScreen
import com.aldeadavila.suggestionbox.ui.screens.profile.info.ProfileScreen

@Composable
fun ClientNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        route = Graph.CLIENT,
        startDestination = ClientScreen.ProductList.route
    ) {
        composable(route = ClientScreen.ProductList.route) {
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

        ClientProudctNavGraph(navController)
    }
}