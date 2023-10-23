package com.aldeadavila.suggestionbox.navigation.graph.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aldeadavila.suggestionbox.navigation.Graph
import com.aldeadavila.suggestionbox.navigation.screen.auth.AuthScreen
import com.aldeadavila.suggestionbox.screens.login.LoginScreen

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        route = Graph.AUTH,
        startDestination =  AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                hiltViewModel(),
                navigateToForgotPasswordScreen = {
                    navController.navigate(
                        route = AuthScreen.Login.route
                    )
                },
                navigateToSignUpScreen = {
                    navController.navigate(
                        route = AuthScreen.Login.route
                    )
                },
                )
        }
    }
}