package com.aldeadavila.suggestionbox.presentation.suggestion_detail

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aldeadavila.suggestionbox.domain.repository.AddImageToStorageResponse
import com.aldeadavila.suggestionbox.presentation.common.NormalTextComponent
import com.aldeadavila.suggestionbox.presentation.suggestion_detail.components.AddImageToStorage
import com.aldeadavila.suggestionbox.presentation.suggestion_detail.components.OpenGallery
import com.aldeadavila.suggestionbox.util.Constants
import kotlinx.coroutines.launch
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionDetailScreen(
    state: SuggestionDetailState,
    addNewSuggestion:(String, String) -> Unit,
    viewModel: SuggestionDetailViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val couroutineScope = rememberCoroutineScope()
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { imageUri->
        imageUri?.let {
            viewModel.addImageToStorage(imageUri)
        }
    }
    
    
    Scaffold (
        content = { paddingValues -> 
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues))
            {
                OpenGallery(
                    openGallery = {
                        galleryLauncher.launch(Constants.ALL_IMAGES)
                    }
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )

    fun showSnackBar() = couroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = "Imagen corrrectamente agregada",
            actionLabel = "Mostrar"
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.getImageFromDatabase()

        }

    }


    AddImageToStorage (
        addImageToDatabase = { downloadUrl ->
           viewModel.addImageToDatabase(downloadUrl)
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = title,
                onValueChange = { title = it},
                label = {
                    Text(text = "Title")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = author,
                onValueChange = { author = it},
                label = {
                    Text(text = "Author")
                }
            )


        }

        if(state.error.isNotBlank()) {
            NormalTextComponent(value = state.error)
        }

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),

                onClick = {
                    addNewSuggestion(title, author)
                },
                colors = ButtonDefaults.buttonColors(
                    Color.Gray
                )
            ) {
                Text(
                    text = "Add New Book",
                    color = Color.Black
                )
            }
        }
    }
}