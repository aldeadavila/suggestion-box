package com.aldeadavila.suggestionbox.presentation.suggestion_detail

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.repository.ImageRepository
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SuggestionDetailViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository,
    private val imageRepository: ImageRepository,
): ViewModel() {

    var addImageToStorageResponse by mutableStateOf<Response<Uri>>(Response.Success(null))
        private set
    var addImageToDatabaseResponse by mutableStateOf<Response<Boolean>>(Response.Success(null))
        private set
    var getImageFromDatabaseResponse by mutableStateOf<Response<String>>(Response.Success(null))
        private set

    private val _state: MutableState<SuggestionDetailState> = mutableStateOf(SuggestionDetailState())
    val state: State<SuggestionDetailState>
        get() = _state

    fun addNewSuggestion(title: String, author: String) {
        val suggestion = Suggestion(
            id = UUID.randomUUID().toString(),
            title = title,
            author = author,
            description = "description",
            category = "category",
            image = "image",
            thumb = "thumb",
        )

        suggestionRepository.addNewSuggestion(suggestion)
    }

    fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = Response.Loading
        addImageToStorageResponse = imageRepository.addImageToStorageResponse(imageUri)
    }

    fun addImageToDatabase(dowloadUri: Uri) = viewModelScope.launch {
        addImageToDatabaseResponse = Response.Loading
        addImageToDatabaseResponse = imageRepository.addImageUrlToFirestore(dowloadUri)
    }

    fun getImageFromDatabase() = viewModelScope.launch {
        getImageFromDatabaseResponse = Response.Loading
        getImageFromDatabaseResponse = imageRepository.getImageFromFirestoreResponse()
    }

}