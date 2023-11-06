package com.aldeadavila.suggestionbox.presentation.profile.component

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.presentation.common.ProgressBar
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.profile.ProfileViewModel
import com.aldeadavila.suggestionbox.util.Constants.ACCESS_REVOKED_MESSAGE
import com.aldeadavila.suggestionbox.util.Constants.REVOKE_ACCESS_MESSAGE
import com.aldeadavila.suggestionbox.util.Constants.SENSITIVE_OPERATION_MESSAGE
import com.aldeadavila.suggestionbox.util.Constants.SIGN_OUT_ITEM
import com.aldeadavila.suggestionbox.util.Utils.Companion.showMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    scaffoldState: SnackbarHostState ,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = scaffoldState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT_ITEM
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isAccessRevoked = revokeAccessResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked == true) {
                    showMessage(context, ACCESS_REVOKED_MESSAGE)
                }
            }
        }
        is Response.Failure -> revokeAccessResponse.apply {
            LaunchedEffect(e) {
                print(e)
                if (e?.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}