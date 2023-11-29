package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.list

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminsuggestionListViewModel @Inject constructor(
    private val suggestionsUseCase: SuggestionsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var data = savedStateHandle.get<String>("category")
    var category = Category.fromJson(data!!)

    var suggestionResponse by mutableStateOf<Resource<List<Suggestion>>?>(null)
        private set

    var suggestionDeleteResponse by mutableStateOf<Resource<Unit>?>(null)
        private set

    init {
        getsuggestions()
    }

    private fun getsuggestions() = viewModelScope.launch {
        suggestionResponse = Resource.Loading
        suggestionsUseCase.findByCategory(category.id!!).collect {
            suggestionResponse = it
        }
    }

    fun deletesuggestion(id: String) = viewModelScope.launch {
        suggestionDeleteResponse = Resource.Loading
        val result = suggestionsUseCase.deleteSuggestion(id)
        suggestionDeleteResponse = result
    }
}