package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailViewModel

@Composable
fun GetCommentsBySuggestion(
    navHostController: NavHostController,
    vm: SuggestionDetailViewModel = hiltViewModel()
) {
    when (val response = vm.commentsResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            CommentListBySuggestionContent(
                navHostController,
                comments = response.data
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