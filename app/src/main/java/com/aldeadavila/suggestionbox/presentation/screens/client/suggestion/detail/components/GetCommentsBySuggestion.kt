package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailViewModel

@Composable
fun GetCommentsBySuggestion(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    vm: SuggestionDetailViewModel = hiltViewModel()
) {
    when (val response = vm.commentsResponse) {
        Resource.Loading -> {
            ProgressBar()
        }

        is Resource.Succes -> {
            ClientCommentListBySuggestionContent(
                navHostController,
                paddingValues = paddingValues,
                comments = response.data
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
                    "Hubo un error desconocido mostrando el listado de comentarios",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}