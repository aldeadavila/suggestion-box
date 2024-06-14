package com.aldeadavila.suggestionbox.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.screens.client.home.ClientHomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        AuthNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            ClientHomeScreen()
        }
    }
}
