package com.aldeadavila.suggestionbox.presentation.navigation.graph.admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.graph.profile.ProfileNavGraph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.admin.AdminScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.list.AdminCategoryListScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileScreen

@Composable
fun AdminNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ADMIN,
        startDestination = AdminScreen.CategoryList.route
    ) {
        /* composable(route = AdminScreen.ProductList.route) {
             AdminProductListScreen()
         }*/

        composable(route = AdminScreen.CategoryList.route) {
            AdminCategoryListScreen(navController)
        }

        composable(route = AdminScreen.Profile.route) {
            ProfileScreen(navController)
        }

        ProfileNavGraph(navController)
        AdminCategoryNavGraph(navController)
    }
}