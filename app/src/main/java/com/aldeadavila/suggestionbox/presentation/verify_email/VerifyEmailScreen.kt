package com.aldeadavila.suggestionbox.presentation.verify_email

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.presentation.profile.ProfileViewModel
import com.aldeadavila.suggestionbox.presentation.profile.component.RevokeAccess
import com.aldeadavila.suggestionbox.presentation.verify_email.components.ReloadUser
import com.aldeadavila.suggestionbox.presentation.verify_email.components.VerifyEmailContent
import com.aldeadavila.suggestionbox.util.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import com.aldeadavila.suggestionbox.util.Utils.Companion.showMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    val scaffoldState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(

        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        snackbarHost = { SnackbarHost(scaffoldState) },
    )

    ReloadUser(
        navigateToProfileScreen = {
            if (viewModel.isEmailVerified) {
                navigateToProfileScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        }
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}