package com.aldeadavila.suggestionbox.navigation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldeadavila.suggestionbox.navigation.Graph
import com.aldeadavila.suggestionbox.navigation.screen.AppScreen
import com.aldeadavila.suggestionbox.presentation.forgotpassword.ForgotPasswordScreen
import com.aldeadavila.suggestionbox.presentation.home.HomeScreen
import com.aldeadavila.suggestionbox.presentation.login.LoginScreen
import com.aldeadavila.suggestionbox.presentation.login.OneTapSignInViewModel
import com.aldeadavila.suggestionbox.presentation.profile.ProfileScreen
import com.aldeadavila.suggestionbox.presentation.register.RegisterScreen
import com.aldeadavila.suggestionbox.presentation.splash.SplashScreen
import com.aldeadavila.suggestionbox.presentation.suggestion_detail.SuggestionDetailScreen
import com.aldeadavila.suggestionbox.presentation.suggestion_detail.SuggestionDetailViewModel


@Composable
fun AuthNavGraph(
    navController: NavHostController,
    oneTapSignInViewModel: OneTapSignInViewModel = hiltViewModel()
    ) {
    NavHost(navController = navController,
        route = Graph.AUTH,
        startDestination =  AppScreen.Splash.route,
    ) {

        composable(route = AppScreen.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = AppScreen.Login.route) {
            LoginScreen(
                hiltViewModel(),
                oneTapSignInViewModel = oneTapSignInViewModel,
                navController = navController,
                navigateToForgotPasswordScreen = {
                    navController.navigate(
                        route = AppScreen.ForgotPasswordScreen.route
                    )
                },
                navigateToSignUpScreen = {
                    navController.navigate(
                        route = AppScreen.Register.route
                    )
                },
                )
        }
        composable(
            route = AppScreen.ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppScreen.Register.route
        ) {
            RegisterScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = AppScreen.Profile.route
        ) {
            ProfileScreen()
        }
        composable(
            route = AppScreen.Home.route
        ) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(
            route = AppScreen.SuggestionDetail.route
        ) {
            val viewModel: SuggestionDetailViewModel = hiltViewModel()
            val state = viewModel.state.value
            SuggestionDetailScreen(
                state =  state,
                addNewSuggestion = viewModel::addNewSuggestion
            )
        }
    }
}