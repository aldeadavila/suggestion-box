package com.aldeadavila.suggestionbox.presentation.register.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.components.ProgressBar
import com.aldeadavila.suggestionbox.domain.model.Resource
import com.aldeadavila.suggestionbox.presentation.register.RegisterViewModel

@Composable
fun SendEmailVerification(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    when(val sendEmailVerificationResponse = viewModel.sendEmailVerificationResource) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> Unit
        is Resource.Error -> sendEmailVerificationResponse.apply {
            LaunchedEffect(this.data) {
                print("Error en verificación de Email")
            }
        }
    }
}