package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.create.ClientCommentCreateState
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.create.mapper.toComment
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.ClientCommentUpdateState
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.mapper.toComment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientSuggestionDetailViewModel @Inject constructor(
    private val commentsUseCase: CommentsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var data = savedStateHandle.get<String>("suggestion")
    var suggestion = Suggestion.fromJson(data!!)
    var listProductImage = listOf<String>(
        suggestion.image1 ?: "",
        suggestion.image2 ?: ""
    )
    var commentsResponse by mutableStateOf<Resource<List<Comment>>?>(null)
    var commentResponse by mutableStateOf<Resource<Comment>?>(null)
    var state by mutableStateOf(ClientCommentCreateState())
    var stateUpdate by mutableStateOf(ClientCommentUpdateState())
    var deleteCommentResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var suggestionUpdateResponse by mutableStateOf<Resource<Comment>?>(null)
        private set

    var user by mutableStateOf<User?> (null)
        private set

    init {
        getComments()
        getSessionDate()

    }

    fun createComment() = viewModelScope.launch {
        commentResponse = Resource.Loading
        val result = commentsUseCase.createCommentUseCase(state.toComment())
        commentResponse = result
    }

    fun updateComment() = viewModelScope.launch {
        suggestionUpdateResponse = Resource.Loading
        val result = commentsUseCase.updateCommentUseCase(
            user?.id ?: "",
            stateUpdate.toComment()
        )
        suggestionUpdateResponse = result
    }
    fun deleteComment(id: String) = viewModelScope.launch {
        deleteCommentResponse = Resource.Loading
        val result = commentsUseCase.deleteCommentUseCase(id)
        deleteCommentResponse = result
    }

    fun onCommentContentInput(input: String) {
        state = state.copy(content = input)
    }


    fun isFromMe(idUser: String): Boolean {
        return user?.id == idUser
    }

    private fun getComments() = viewModelScope.launch {
        commentsResponse = Resource.Loading
        commentsUseCase.findBySuggestionUseCase(suggestion.id!!).collect {
            commentsResponse =  it
        }
    }

    private fun getSessionDate() = viewModelScope.launch {
        authUseCase.getSessionData().collect() { data ->
            user = data.user
            state = state.copy(idUser = user?.id!!)
            state = state.copy(idSuggestion = suggestion?.id!!)
        }
    }

}