package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCases
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.CategoryRadioButton
import com.aldeadavila.suggestionbox.presentation.util.ComposeFileProvider
import com.aldeadavila.suggestionbox.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ClientSuggestionUpdateViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val suggestionsUseCases: SuggestionsUseCases
) : ViewModel() {

    var state by mutableStateOf(ClientSuggestionUpdateState())
        private set

    var suggestionResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var data = savedStateHandle.get<String>("suggestion")
    var suggestion = Suggestion.fromJson(data!!)

    // IMAGENES
    var file1: File? = null
    var file2: File? = null
    var files: MutableList<File> = mutableListOf()
    val resultingActivityHandler = ResultingActivityHandler()

    val radioOptions = listOf(
        CategoryRadioButton("Agradecimientos", R.drawable.ic_check),
        CategoryRadioButton("Quejas", R.drawable.ic_forbid),
        CategoryRadioButton("Sugerencias", R.drawable.ic_cognition),
    )
    init {
        state = state.copy(
            title = suggestion.title,
            description = suggestion.description,
            idUser = suggestion.user_id,
            category = suggestion.category,
            image1 = suggestion.images.get(0),
            image2 = suggestion.images.get(1),
        )
    }

    fun onUpdateSuggestion() {
        val suggestion = Suggestion(
            suggestion_id = suggestion.suggestion_id,
            title = state.title,
            description = state.description,
            category = state.category,
            images = mutableListOf(state.image1, state.image2),
            user_id = suggestion.user_id,
            created_at = suggestion.created_at
        )
        updateSuggestion(suggestion)
    }

    fun updateSuggestion(suggestion: Suggestion) = viewModelScope.launch {
        suggestionResponse = Response.Loading
        if (file1 != null) {
            files.add(0, file1!!)
        }
        if (file2 != null) {
            files.add(1, file2!!)
        }
        val result = suggestionsUseCases.updateSuggestion(suggestion, files.toList())
        suggestionResponse = result
        files.clear()
        file1 = null
        file2 = null
    }

    fun pickImage(imageNumber: Int) = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*") // URI
        if (result != null) {
            if (imageNumber == 1) {
                file1 = ComposeFileProvider.createFileFromUri(
                    context,
                    result
                )
                state = state.copy(image1 = result.toString())
            } else if (imageNumber == 2) {
                file2 = ComposeFileProvider.createFileFromUri(
                    context,
                    result
                )
                state = state.copy(image2 = result.toString())
            }
        }
    }

    fun takePhoto(imageNumber: Int) = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            if (imageNumber == 1) {
                state = state.copy(
                    image1 = ComposeFileProvider.getPathFromBitmap(
                        context,
                        result
                    )
                )
                file1 = File(state.image1)
            } else if (imageNumber == 2) {
                state = state.copy(
                    image2 = ComposeFileProvider.getPathFromBitmap(
                        context,
                        result
                    )
                )
                file2 = File(state.image2)
            }
        }
    }

    fun onNameInput(input: String) {
        state = state.copy(title = input)
    }

    fun onDescriptionInput(input: String) {
        state = state.copy(description = input)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
    }

    fun onIdUserInput(input: String) {
        state = state.copy(idUser = input)
    }
}
