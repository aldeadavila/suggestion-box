package com.aldeadavila.suggestionbox.presentation.screens.client.category.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.category.list.ClientCategoryListViewModel
import com.aldeadavila.suggestionbox.presentation.screens.profile.components.ClientCategoryListContent

@Composable
fun GetCategories(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    vm: ClientCategoryListViewModel = hiltViewModel()
) {

    when (val response = vm.categoriesResponse) {
        Resource.Loading -> {
            ProgressBar()
        }

        is Resource.Succes -> {

            ClientCategoryListContent(
                paddingValues = paddingValues,
                navHostController,
                categories = response.data
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