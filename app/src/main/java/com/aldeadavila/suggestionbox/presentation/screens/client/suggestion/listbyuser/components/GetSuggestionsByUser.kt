package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbyuser.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbyuser.SuggestionListByUserViewModel

@Composable
fun GetSuggestionsByUser(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    vm: SuggestionListByUserViewModel = hiltViewModel()
) {

    when (val response = vm.suggestionResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success-> {

            SuggestionListByUserContent(
                navHostController,
                paddingValues = paddingValues,
                suggestions = response.data
            )

        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {
        }
    }

}