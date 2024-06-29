package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCases
import com.aldeadavila.suggestionbox.presentation.util.ComposeFileProvider
import com.aldeadavila.suggestionbox.presentation.util.ResultingActivityHandler
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SuggestionCreateViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val suggestionUseCase: SuggestionsUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var state by mutableStateOf(SuggestionCreateState())
        private set

    var suggestionResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //imagenes
    var file1: File? = null
    var file2: File? = null
    var files: List<File> = listOf()
    val resultingActivityHandler = ResultingActivityHandler()

    val currentUser = authUseCases.getCurrentUser()

    val radioOptions = listOf(
        CategoryRadioButton("Agradecimientos", R.drawable.ic_check),
        CategoryRadioButton("Quejas", R.drawable.ic_forbid),
        CategoryRadioButton("Sugerencias", R.drawable.ic_cognition),
    )

    init {

    }

    fun createSuggestion(suggestion: Suggestion) = viewModelScope.launch {
        if (file1 != null && file2 != null) {
            files = listOf(
                file1!!,
                file2!!
            )
            suggestionResponse = Response.Loading
            val result = suggestionUseCase.createSuggestionUseCase(suggestion, files)
            suggestionResponse = result
        }

    }


    fun pickImage(imageNumber: Int) = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
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

    fun clearForm() {
        state = state.copy(
            title = "",
            description = "",
            image1 = "",
            image2 = "",
            idUser = "",
            category = ""
        )
        suggestionResponse = null
    }

    fun onTitleInput(input: String) {
        state = state.copy(title = input)
    }

    fun onDescriptionInput(input: String) {
        state = state.copy(description = input)
    }


    fun onCategoryInput(category: String) {
        state = state.copy(category = category.lowercase())
    }

    fun onNewSuggestion() {
        val suggestion =
            Suggestion(
                title = state.title,
                description = state.description,
                category = state.category,
                images = mutableListOf(),
                user_id = currentUser?.uid ?: "",
                created_at = Timestamp.now()
            )
        createSuggestion(suggestion)
    }

}

data class CategoryRadioButton(
    var category: String,
    var image: Int
)