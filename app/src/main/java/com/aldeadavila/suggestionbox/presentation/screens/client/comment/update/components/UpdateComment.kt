package com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.ClientCommentUpdateViewModel

@Composable
fun UpdateComment(vm: ClientCommentUpdateViewModel = hiltViewModel()) {

    when(val response = vm.commentResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            Toast.makeText(LocalContext.current, "El comentario se ha actualizado correctamente", Toast.LENGTH_LONG).show()
        }

        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {
        }
    }
}