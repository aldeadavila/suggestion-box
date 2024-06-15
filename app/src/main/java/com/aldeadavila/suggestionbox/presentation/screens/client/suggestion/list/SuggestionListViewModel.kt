package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCases
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionListViewModel @Inject constructor(
    private val suggestionsUseCases: SuggestionsUseCases,
    private val authUseCases: AuthUseCases
)  :ViewModel() {

    var suggestionsResponse by mutableStateOf<Response<List<Suggestion>>?>(null)
        private set
    var deleteSuggestionResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var user by mutableStateOf<User?> (null)
        private set


    init {
        getSuggestions()
    }


    fun getSuggestions() = viewModelScope.launch {
        suggestionsResponse = Response.Loading
        suggestionsUseCases.getSuggestions().collect { response ->
            suggestionsResponse = response
        }
    }


    fun getEditable(idUser: String): Boolean {
        return idUser == user?.id
    }

    fun printDescription(description:String): String {
        if(description.length > 110) {
            return description.substring(0,110) + "..."
        } else {
            return description
        }
    }

    fun printTitle(title:String): String {
        if(title.length > 28) {
            return title.substring(0,28) + "..."
        } else {
            return title
        }
    }


}
