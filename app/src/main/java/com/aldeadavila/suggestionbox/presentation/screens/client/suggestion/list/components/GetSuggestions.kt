package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.ClientSuggestionListViewModel

@Composable
fun GetSuggestions(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    vm: ClientSuggestionListViewModel = hiltViewModel()
) {

    when (val response = vm.suggestionsResponse) {
        Resource.Loading -> {
            ProgressBar()
        }

        is Resource.Succes -> {

            ClientSuggestionListContent(
                navHostController,
                paddingValues = paddingValues,
                suggestions = response.data,
                user = vm.user
            )

        }

        is Resource.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.message,
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {
            if (response != null) {
                Toast.makeText(
                    LocalContext.current,
                    "Hubo un error desconocido",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}