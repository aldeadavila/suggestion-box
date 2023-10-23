package com.aldeadavila.suggestionbox.navigation.screen.auth

sealed class AuthScreen(val route: String) {
    object  Login: AuthScreen("login")
    object Register: AuthScreen("register")
    object ForgotPasswordScreen: AuthScreen("forgot_password")
}
