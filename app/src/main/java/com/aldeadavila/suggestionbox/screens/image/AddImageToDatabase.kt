package com.aldeadavila.suggestionbox.screens.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.components.ProgressBar
import com.aldeadavila.suggestionbox.model.Response

@Composable
fun AddImageToDatabase(
    viewModel: ImageViewModel = hiltViewModel(),
    showSnackBar: (isImageAddedToDatabase: Boolean)-> Unit
) {
    when (val addImageToDatabaseResponse = viewModel.addImageToDatabaseResponse){
        is Response.Loading -> ProgressBar()
        is Response.Success -> addImageToDatabaseResponse.data?.let { isImageAddedToDatabase ->
            LaunchedEffect(isImageAddedToDatabase){
                showSnackBar(isImageAddedToDatabase)
                //mostrar aquí
            }
        }
        is Response.Failure -> print(addImageToDatabaseResponse.e)
    }
    
}