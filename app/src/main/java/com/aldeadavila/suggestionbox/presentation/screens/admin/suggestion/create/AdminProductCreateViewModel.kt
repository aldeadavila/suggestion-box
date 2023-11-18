package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.mapper.toProduct
import com.aldeadavila.suggestionbox.presentation.util.ComposeFileProvider
import com.aldeadavila.suggestionbox.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdminProductCreateViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val productUseCase: SuggestionsUseCase
) : ViewModel() {

    var state by mutableStateOf(AdminSuggestionCreateState())
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
    }

    fun createProduct() = viewModelScope.launch {
        if (file1 != null && file2 != null) {
            files = listOf(
                file1!!,
                file2!!
            )
            suggestionResponse = Resource.Loading
            val result = productUseCase.createSuggestionUseCase(
                state.toProduct(),
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
            price = 0.0,
        )
        suggestionResponse = null
    }

    fun onNameInput(input: String) {
        state = state.copy(name = input)
    }

    fun onDescriptionInput(input: String) {
        state = state.copy(description = input)
    }

    fun onPriceInput(input: String) {
        state = state.copy(price = input.toDouble())
    }
}