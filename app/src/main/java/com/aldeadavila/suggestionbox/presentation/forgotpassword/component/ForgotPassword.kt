package com.aldeadavila.suggestionbox.presentation.forgotpassword.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.presentation.common.ProgressBar
import com.aldeadavila.suggestionbox.presentation.forgotpassword.ForgotPasswordViewModel
import com.aldeadavila.suggestionbox.domain.model.Resource.*


@Composable
fun ForgotPassword(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    showResetPasswordMessage: () -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val sendPasswordResetEmailResponse = viewModel.sendPasswordResetEmailResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isPasswordResetEmailSent = sendPasswordResetEmailResponse.data
            LaunchedEffect(isPasswordResetEmailSent) {
                if (isPasswordResetEmailSent == true) {
                    navigateBack()
                    showResetPasswordMessage()
                }
            }
        }
        is Error -> sendPasswordResetEmailResponse.apply {
            LaunchedEffect(this.data) {

                print("error sending password")
            }
        }
    }
}