package com.aldeadavila.suggestionbox.presentation.screens.admin.home.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aldeadavila.suggestionbox.presentation.navigation.screen.admin.AdminScreen

@Composable
fun AdminBottomBar(navHostController: NavHostController) {

    val screens = listOf(
       // AdminScreen.SuggestionList,
        AdminScreen.CategoryList,
        AdminScreen.Profile
    )

    val navBackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route}

    if (bottomBarDestination) {
        BottomAppBar() {
            screens.forEach { screen ->
                AdminBottomBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        }
    }
}