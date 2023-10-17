package com.aldeadavila.complaint.screens.image

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.components.ProgressBar
import com.aldeadavila.suggestionbox.model.Response
import com.aldeadavila.suggestionbox.screens.image.ImageViewModel

@Composable
fun GetImageFromDatabase(
    viewModel: ImageViewModel = hiltViewModel(),
    createImageContent: @Composable (imageUrl: String)-> Unit
) {
    when (val getImageFromFirestoreResponse = viewModel.getImageFromDatabaseResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> getImageFromFirestoreResponse.data?.let { imageUrl ->
            createImageContent(imageUrl)
        }
        is Response.Failure -> print (getImageFromFirestoreResponse.e)
    }
}