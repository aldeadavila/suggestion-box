package com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.ClientCommentUpdateViewModel

@Composable
fun UpdateComment(vm: ClientCommentUpdateViewModel = hiltViewModel()) {

    when(val response = vm.commentResponse) {
        Resource.Loading -> {
            ProgressBar()
        }
        is Resource.Succes -> {
            Toast.makeText(LocalContext.current, "El comentario se ha actualizado correctamente", Toast.LENGTH_LONG).show()
        }

        is Resource.Failure -> {
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_LONG).show()
        }

        else -> {
            if(response != null) {
                Toast.makeText(LocalContext.current, "Hubo un error desconocido", Toast.LENGTH_LONG).show()
            }
        }
    }
}