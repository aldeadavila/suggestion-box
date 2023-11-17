package com.aldeadavila.suggestionbox.ui.navigation.graph.roles

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.ui.navigation.Graph
import com.aldeadavila.suggestionbox.ui.navigation.screen.roles.RolesScreen
import com.aldeadavila.suggestionbox.ui.screens.admin.home.AdminHomeScreen
import com.aldeadavila.suggestionbox.ui.screens.client.home.ClientHomeScreen
import com.aldeadavila.suggestionbox.ui.screens.roles.RolesScreen

fun NavGraphBuilder.RolesNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ROLES,
        startDestination = RolesScreen.Roles.route
    ) {

        composable(route = RolesScreen.Roles.route) {
            RolesScreen(navController)
        }

        composable(route = Graph.CLIENT) {
            ClientHomeScreen()
        }

        composable(route = Graph.ADMIN) {
            AdminHomeScreen()
        }

    }
}