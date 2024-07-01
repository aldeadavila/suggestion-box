package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbyuser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionListByUserViewModel @Inject constructor(
    private val suggestionsUseCases: SuggestionsUseCases,
    private val savedStateHandle: SavedStateHandle,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    val currentUser = authUseCases.getCurrentUser()

    var suggestionResponse by mutableStateOf<Response<List<Suggestion>>?>(null)
        private set
    var deleteSuggestionResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var userData by mutableStateOf(User())
        private set


    fun getSuggestions() = viewModelScope.launch {
        suggestionResponse = Response.Loading
        suggestionsUseCases.getSuggestionsByUserUseCase(currentUser?.uid ?: "").collect {
            suggestionResponse = it
        }
    }

    fun getEditable(idUser: String): Boolean {
        return idUser == currentUser?.uid || userData.roles?.contains("admin") ?: false
    }

    fun printDescription(description: String): String {
        if (description.length > 110) {
            return description.substring(0, 110) + "..."
        } else {
            return description
        }
    }

    fun printTitle(title: String): String {
        if (title.length > 28) {
            return title.substring(0, 28) + "..."
        } else {
            return title
        }
    }

    fun deleteSuggestion(idSuggestion: String) = viewModelScope.launch {
        deleteSuggestionResponse = Response.Loading
        val result = suggestionsUseCases.deleteSuggestion(idSuggestion)
        deleteSuggestionResponse = result
    }

}