package com.aldeadavila.suggestionbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aldeadavila.suggestionbox.navigation.graph.AuthNavGraph
import com.aldeadavila.suggestionbox.navigation.screen.AppScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            AuthNavGraph(navController)
            AuthState()
        }
    }


    @Composable
    private fun AuthState() {
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        if (isUserSignedOut) {
            NavigateToSignInScreen()
        } else {
            if (viewModel.isEmailVerified) {
                NavigateToProfileScreen()
            } else {
               NavigateToVerifyEmailScreen()
            }
        }
    }

    @Composable
    private fun NavigateToSignInScreen() = navController.navigate(AppScreen.Login.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToProfileScreen() = navController.navigate(AppScreen.Profile.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

   @Composable
    private fun NavigateToVerifyEmailScreen() = navController.navigate(AppScreen.VerifyEmail.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}
