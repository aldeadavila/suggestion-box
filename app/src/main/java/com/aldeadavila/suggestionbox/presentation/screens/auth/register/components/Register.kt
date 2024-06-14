package com.aldeadavila.suggestionbox.presentation.screens.auth.register.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.screens.auth.register.RegisterViewModel

@Composable
fun Register(navHostController: NavHostController, vm: RegisterViewModel = hiltViewModel()) {

    when(val registerResponse = vm.registerResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, registerResponse.exception.message ?: "Error desconocido", Toast.LENGTH_LONG)
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                vm.createUser()
                navHostController.popBackStack(Graph.AUTH, inclusive = true)
                navHostController.navigate(route = Graph.HOME)
            }
        }
        else -> {}
    }

}