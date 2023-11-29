package com.aldeadavila.suggestionbox.presentation.screens.profile.update.components

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.profile.update.ProfileUpdateViewModel

@Composable
fun UpdateUser(vm: ProfileUpdateViewModel = hiltViewModel()) {

    when(val response = vm.updateResponse) {
        Resource.Loading -> {
            ProgressBar()
        }
        is Resource.Succes -> {
            Log.d("UpdateUser", "Data: ${response.data}")
            vm.updateUserSession(response.data)
            Toast.makeText(LocalContext.current, "Los datos se han actualizado correctamente", Toast.LENGTH_LONG).show()
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