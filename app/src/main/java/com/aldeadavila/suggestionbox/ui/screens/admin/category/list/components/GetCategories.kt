package com.aldeadavila.suggestionbox.ui.screens.admin.category.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.ui.components.ProgressBar
import com.aldeadavila.suggestionbox.ui.screens.admin.category.list.AdminCategoryListViewModel

@Composable
fun GetCategories(navHostController: NavHostController, paddingValues: PaddingValues, vm: AdminCategoryListViewModel = hiltViewModel()) {

    when(val response = vm.categoriesResponse) {
        Resource.Loading -> {
            ProgressBar()
        }
        is Resource.Succes -> {

           AdminScreenCategoryListContent(navHostController, categories = response.data, paddingValues = paddingValues)

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