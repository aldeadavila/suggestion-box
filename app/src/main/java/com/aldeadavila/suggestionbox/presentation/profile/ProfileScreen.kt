package com.aldeadavila.suggestionbox.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.profile.component.ProfileContent
import com.aldeadavila.suggestionbox.presentation.profile.component.RevokeAccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val scaffoldState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            ProfileContent(
                navController = navController,
                padding = padding
            )
        },
        snackbarHost = { SnackbarHost(scaffoldState) },
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}