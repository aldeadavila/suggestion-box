package com.aldeadavila.suggestionbox.presentation.screens.auth.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.screens.auth.login.LoginViewModel

@Composable
fun Login(navHostController: NavHostController,viewModel: LoginViewModel =  hiltViewModel()) {

    when(val loginRespponse = viewModel.loginResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                navHostController.navigate(route = Graph.HOME) {
                    popUpTo(Graph.AUTH) { inclusive = true }
                }
            }

        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginRespponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }

}