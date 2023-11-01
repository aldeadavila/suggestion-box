package com.aldeadavila.suggestionbox.presentation.register.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.components.ProgressBar
import com.aldeadavila.suggestionbox.domain.model.Resource
import com.aldeadavila.suggestionbox.presentation.register.RegisterViewModel

@Composable
fun SignUp(
    viewModel: RegisterViewModel = hiltViewModel(),
    sendEmailVerification: () -> Unit,
    showVerifyEmailMessage: () -> Unit
) {
    when(val signUpResponse = viewModel.signUpResource) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> {
            val isUserSignedUp = signUpResponse.data
            LaunchedEffect(isUserSignedUp) {
                if (isUserSignedUp == true) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                }
            }
        }
        is Resource.Error -> signUpResponse.apply {
            LaunchedEffect(this.data) {
                print("Error al darse de alta")
            }
        }
    }
}