package com.aldeadavila.suggestionbox.presentation.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.login.component.LoginContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    oneTapSignInViewModel: OneTapSignInViewModel,
    navController: NavHostController,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
) {

    Scaffold (

        content = { padding ->
            LoginContent(
                padding = padding,
                viewModel = viewModel,
                oneTapSignInViewModel = oneTapSignInViewModel,
                navController = navController,
                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                navigateToSignUpScreen = navigateToSignUpScreen
            )
        }
    )

}