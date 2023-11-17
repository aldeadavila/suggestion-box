package com.aldeadavila.suggestionbox.ui.navigation.screen.auth

sealed class AuthScreen(val route: String) {
    object  Login: AuthScreen("login")
    object Register: AuthScreen("register")

}
