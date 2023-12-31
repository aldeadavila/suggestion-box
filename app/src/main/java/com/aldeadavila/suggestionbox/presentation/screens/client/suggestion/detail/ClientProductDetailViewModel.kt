package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var data = savedStateHandle.get<String>("suggestion")
    var suggestion = Suggestion.fromJson(data!!)
    var listProductImage = listOf<String>(
        suggestion.image1 ?: "",
        suggestion.image2 ?: ""
    )

}