package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.SuggestionListViewModel

@Composable
fun GetSuggestions(
    navHostController: NavHostController,
    vm: SuggestionListViewModel = hiltViewModel()
) {

    when (val response = vm.suggestionsResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {

            SuggestionListContent(
                navHostController,
                suggestions = response.data,
                user = vm.user
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