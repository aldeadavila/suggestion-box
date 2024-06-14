package com.aldeadavila.suggestionbox.presentation.screens.client.home.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aldeadavila.suggestionbox.presentation.navigation.graph.HomeBottomBarScreen

@Composable
fun ClientBottomBar(navHostController: NavHostController) {

    val screens = listOf(
        HomeBottomBarScreen.SuggestionList,
        HomeBottomBarScreen.CategoryList,
        HomeBottomBarScreen.Profile
    )

    val navBackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route}

    if (bottomBarDestination) {
        BottomAppBar() {
            screens.forEach { screen ->
                ClientBottomBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        }
    }
}