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
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionListViewModel @Inject constructor(
    private val suggestionsUseCases: SuggestionsUseCases,
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    var suggestionsResponse by mutableStateOf<Response<List<Suggestion>>?>(null)
        private set
    var deleteSuggestionResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var currentUser = authUseCases.getCurrentUser()
    var userData by mutableStateOf(User())
        private set

    fun getSuggestions() = viewModelScope.launch {
        suggestionsResponse = Response.Loading
        suggestionsUseCases.getSuggestionsUseCase().collect { response ->
            suggestionsResponse = response
        }
    }

    fun deleteSuggestion(idSuggestion: String) = viewModelScope.launch {
        deleteSuggestionResponse = Response.Loading
        val result = suggestionsUseCases.deleteSuggestion(idSuggestion)
        deleteSuggestionResponse = result
    }


    fun getEditable(idUser: String): Boolean {
        getUserById()
        return idUser == currentUser?.uid || userData.roles?.contains("admin") ?: false
    }

    fun isAnonymous() = currentUser!!.isAnonymous

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserByIdUseCase(authUseCases.getCurrentUser()!!.uid).collect {
            userData = it
        }
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


}
