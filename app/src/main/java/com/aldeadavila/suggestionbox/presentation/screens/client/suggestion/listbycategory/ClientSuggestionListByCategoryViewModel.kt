package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbycategory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientSuggestionListByCategoryViewModel @Inject constructor(
    private val suggestionsUseCases: SuggestionsUseCases,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var data = savedStateHandle.get<String>("category")
    var category = Category.fromJson(data!!)

    var suggestionResponse by mutableStateOf<Response<List<Suggestion>>?>(null)
        private set

    init {
        getSuggestions()
    }

    private fun getSuggestions() = viewModelScope.launch {
        suggestionResponse = Response.Loading
       /* suggestionsUseCase.findByCategory(category.id!!).collect {
            suggestionResponse = it
        }*/
    }

}