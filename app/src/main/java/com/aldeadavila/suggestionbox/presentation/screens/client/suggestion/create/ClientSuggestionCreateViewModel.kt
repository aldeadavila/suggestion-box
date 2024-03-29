package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.mapper.toSuggestion
import com.aldeadavila.suggestionbox.presentation.util.ComposeFileProvider
import com.aldeadavila.suggestionbox.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ClientSuggestionCreateViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val suggestionUseCase: SuggestionsUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var state by mutableStateOf(ClientSuggestionCreateState())
        private set

    var user by mutableStateOf<User?> (null)
        private set

    var suggestionResponse by mutableStateOf<Resource<Suggestion>?>(null)

    var data = savedStateHandle.get<String>("category")
    var category = Category.fromJson(data!!)

    //imagenes
    var file1: File? = null
    var file2: File? = null
    var files: List<File> = listOf()
    val resultingActivityHandler = ResultingActivityHandler()

    init {
        state = state.copy(idCategory = category.id ?: "")
        getSessionDate()
    }

    fun createSuggestion() = viewModelScope.launch {
        if (file1 != null && file2 != null) {
            files = listOf(
                file1!!,
                file2!!
            )
            state = user?.id?.let { state.copy(idUser = it) }!!
            suggestionResponse = Resource.Loading
            val result = suggestionUseCase.createSuggestionUseCase(
                state.toSuggestion(),
                files
            )
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
            name = "",
            description = "",
            image1 = "",
            image2 = "",
            idUser = "",
        )
        suggestionResponse = null
    }

    fun onNameInput(input: String) {
        state = state.copy(name = input)
    }

    fun onDescriptionInput(input: String) {
        state = state.copy(description = input)
    }

    fun onIdUserInput(input: String) {
        state = state.copy(idUser = input)
    }

    fun getSessionDate() = viewModelScope.launch {
        authUseCase.getSessionData().collect() { data ->
            user = data.user
        }
    }
}