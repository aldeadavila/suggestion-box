package com.aldeadavila.suggestionbox.ui.screens.admin.category.create.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.ui.components.ProgressBar
import com.aldeadavila.suggestionbox.ui.screens.admin.category.create.AdminCategoryCreateViewModel

@Composable
fun CreateCategory(vm: AdminCategoryCreateViewModel = hiltViewModel()) {

    when (val response = vm.categoryResponse) {
        Resource.Loading -> {
            ProgressBar()
        }

        is Resource.Succes -> {
            /*Log.d("UpdateUser", "Data: ${response.data}")
            vm.categoryResponse(response.data)*/
            vm.clearForm()
            Toast.makeText(
                LocalContext.current,
                "La categoría se ha añadido correctamente",
                Toast.LENGTH_LONG
            ).show()
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