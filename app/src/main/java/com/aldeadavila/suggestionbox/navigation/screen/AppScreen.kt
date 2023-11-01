package com.aldeadavila.suggestionbox.navigation.screen

sealed class AppScreen(val route: String) {
    object Splash: AppScreen("splash")
    object Login: AppScreen("login")
    object Register: AppScreen("register")
    object ForgotPasswordScreen: AppScreen("forgot_password")
    object Profile: AppScreen("profile")
    object Home : AppScreen("Home")
}
