package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientSuggestionDetailViewModel @Inject constructor(
    private val commentsUseCase: CommentsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var data = savedStateHandle.get<String>("suggestion")
    var suggestion = Suggestion.fromJson(data!!)
    var listProductImage = listOf<String>(
        suggestion.image1 ?: "",
        suggestion.image2 ?: ""
    )
    var commentsResponse by mutableStateOf<Resource<List<Comment>>?>(null)

    init {
        getComments()
    }

    fun isFromMe(idUser: String): Boolean {
        return suggestion.idUser == idUser
    }

    private fun getComments() = viewModelScope.launch {
        commentsResponse = Resource.Loading
        commentsUseCase.findBySuggestionUseCase(suggestion.id!!).collect {
            commentsResponse =  it
        }
    }

}