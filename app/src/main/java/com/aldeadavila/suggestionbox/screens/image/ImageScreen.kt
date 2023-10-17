package com.aldeadavila.suggestionbox.screens.image

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aldeadavila.complaint.screens.image.GetImageFromDatabase
import com.aldeadavila.suggestionbox.utils.Constants.ALL_IMAGES
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen(
    viewModel: ImageViewModel = hiltViewModel(),
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        imageUri-> imageUri?.let { viewModel.addImageToStorage(imageUri) }

    }

    Scaffold (
        content = { padding->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {
                AbrirGaleria(
                    openGallery = {
                        galleryLauncher.launch(ALL_IMAGES)
                    }
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
    AddImageToStorage(
        addImageToDatabase = { downloadUrl->
            viewModel.addImageToDatabase(downloadUrl)
        }
    )


    fun showSnackBar() = scope.launch {
        val result = snackbarHostState.showSnackbar(
            message = "Imagen corrrectamente agregada",
            actionLabel = "Mostrar"
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.getImageFromDatabase()

        }

    }

    AddImageToDatabase(
        showSnackBar = { isImageAddedToDatabase ->
            if (isImageAddedToDatabase){
                showSnackBar()

            }

        }
    )

    GetImageFromDatabase(
        createImageContent = { imageUrl ->
            ImageContent(imageUrl = imageUrl)
        }
    )

}
