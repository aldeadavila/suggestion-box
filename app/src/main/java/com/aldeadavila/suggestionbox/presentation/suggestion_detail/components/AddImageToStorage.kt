package com.aldeadavila.suggestionbox.presentation.suggestion_detail.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.domain.model.Response.*
import com.aldeadavila.suggestionbox.presentation.common.ProgressBar
import com.aldeadavila.suggestionbox.presentation.suggestion_detail.SuggestionDetailViewModel


@Composable
fun AddImageToStorage(
    viewModel: SuggestionDetailViewModel = hiltViewModel(),
    addImageToDatabase: (downloadUrl: Uri)->Unit
) {
    when (val addImageToStorageResponse = viewModel.addImageToStorageResponse) {
        is Loading -> ProgressBar()
        is Success -> addImageToStorageResponse.data?.let {downloadUri->
            LaunchedEffect(downloadUri ) {
                addImageToDatabase(downloadUri)
            }
        }
        is Failure -> print(addImageToStorageResponse.e)
    }
}