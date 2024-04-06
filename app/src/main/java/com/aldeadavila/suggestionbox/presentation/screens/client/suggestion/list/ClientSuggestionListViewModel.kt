package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientSuggestionListViewModel @Inject constructor(
    private val suggestionsUseCase: SuggestionsUseCase,
    private val authUseCase: AuthUseCase
)  :ViewModel() {

    var suggestionsResponse by mutableStateOf<Resource<List<Suggestion>>?>(null)
        private set
    var deleteSuggestionResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var user by mutableStateOf<User?> (null)
        private set


    init {
        getSuggestions()
        getSessionDate()
    }

    fun deleteSuggestion(id: String) = viewModelScope.launch {
        deleteSuggestionResponse = Resource.Loading
        val result = suggestionsUseCase.deleteSuggestion(id)
        deleteSuggestionResponse = result
    }

    fun getSuggestions() = viewModelScope.launch {
        suggestionsResponse = Resource.Loading
        suggestionsUseCase.findAll().collect() {
            suggestionsResponse = it
        }
    }

    fun getSessionDate() = viewModelScope.launch {
        authUseCase.getSessionData().collect() { data ->
            user = data.user
        }
    }

    fun getEditable(idUser: String): Boolean {
        return idUser == user?.id
    }


}
