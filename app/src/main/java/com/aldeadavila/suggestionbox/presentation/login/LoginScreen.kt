package com.aldeadavila.suggestionbox.presentation.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.aldeadavila.suggestionbox.presentation.login.content.LoginContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
) {

    Scaffold (
        content = { padding ->
            LoginContent(
                padding = padding,
                signIn = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                navigateToSignUpScreen = navigateToSignUpScreen
            )
        }
    )

}