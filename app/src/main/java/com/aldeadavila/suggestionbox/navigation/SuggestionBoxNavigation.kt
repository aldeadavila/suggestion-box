package com.aldeadavila.suggestionbox.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.aldeadavila.suggestionbox.screens.SplashScreen
import com.aldeadavila.suggestionbox.screens.home.HomeScreen
import com.aldeadavila.suggestionbox.screens.image.ImageScreen
import com.aldeadavila.suggestionbox.screens.login.LoginScreen

@Composable
fun SuggestionBoxNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
            startDestination = ScreenRoutes.SplashScreen.name
    ) {
        composable(ScreenRoutes.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(ScreenRoutes.HomeScreen.name) {
            HomeScreen(navController = navController)

        }
        composable(ScreenRoutes.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(ScreenRoutes.ImageScreen.name) {
            ImageScreen(navController = navController)
        }

    }
}