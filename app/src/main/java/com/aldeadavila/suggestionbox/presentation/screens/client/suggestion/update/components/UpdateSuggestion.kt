package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.components

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.ClientSuggestionUpdateViewModel


@Composable
fun UpdateSuggestion(vm: ClientSuggestionUpdateViewModel = hiltViewModel()) {

    when (val response = vm.suggestionResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            Log.d("UpdateUser", "Data: ${response.data}")
            //vm.categoryResponse(response.data)

            Toast.makeText(
                LocalContext.current,
                "La sugerencia se ha actualizado correctamente",
                Toast.LENGTH_LONG
            ).show()
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }

}

