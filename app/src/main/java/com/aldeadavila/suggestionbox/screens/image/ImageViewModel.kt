package com.aldeadavila.suggestionbox.screens.image

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.model.Response
import com.aldeadavila.suggestionbox.model.Response.Success
import com.aldeadavila.suggestionbox.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repo: ImageRepository
): ViewModel() {
    var addImageToStorageResponse by mutableStateOf<Response<Uri>>(Success(null))
        private set
    var addImageToDatabaseResponse by mutableStateOf<Response<Boolean>>(Success(null))
        private set
    var getImageFromDatabaseResponse by mutableStateOf<Response<String>>(Success(null))
        private set

    fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = Response.Loading
        addImageToStorageResponse = repo.addImageToFirebaseStorage(imageUri)
    }

    fun addImageToDatabase(downloadUrl: Uri) = viewModelScope.launch {
        addImageToDatabaseResponse = Response.Loading
        addImageToDatabaseResponse = repo.addImageUrlToFirestore(downloadUrl)
    }

    fun getImageFromDatabase() = viewModelScope.launch {
        getImageFromDatabaseResponse = Response.Loading
        getImageFromDatabaseResponse = repo.getImageUrlFromFirestore()
    }
}