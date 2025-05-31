package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCases
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.create.CommentCreateState
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components.GetUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionDetailViewModel @Inject constructor(
    private val commentsUseCases: CommentsUseCases,
    val savedStateHandle: SavedStateHandle,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var suggestion by mutableStateOf<Suggestion?>(null)
        private set
    
    var listSuggestionImage by mutableStateOf<List<String>>(emptyList())
        private set
        
    var commentsResponse by mutableStateOf<Response<List<Comment>>?>(null)
        private set
        
    var commentResponse by mutableStateOf<Response<Boolean>?>(null)
    var state by mutableStateOf(CommentCreateState())
    var stateUser by mutableStateOf(GetUserState())

    val currentUser = authUseCases.getCurrentUser()
    var errorMessage by mutableStateOf("")

    init {
        savedStateHandle.get<String>("suggestion")?.let { data ->
            suggestion = Suggestion.fromJson(data)
            suggestion?.let { sug ->
                listSuggestionImage = listOf(sug.images[0], sug.images[1])
                getComments()
            }
        }
    }

    fun createComment(comment: Comment) = viewModelScope.launch {
        if (isValidateForm()) {
            commentResponse = Response.Loading
            val result = commentsUseCases.createCommentUseCase(comment)
            commentResponse = result
            clearComment()
        }
    }

    fun onCommentContentInput(input: String) {
        state = state.copy(content = input)
    }

    fun isFromMe(idUser: String): Boolean {
        val isMine = currentUser?.uid == idUser
        Log.d("ISFROMME", isMine.toString())
        return currentUser?.uid == idUser
    }

    fun isValidateForm(): Boolean {
        if (state.content == "") {
            errorMessage = "Los comentarios no pueden estar vacíos"
            return false
        }
        return true
    }

    private fun clearComment() {
        state = state.copy(content = "")
    }

    private fun getComments() = viewModelScope.launch {
        suggestion?.let { sug ->
            commentsResponse = Response.Loading
            commentsUseCases.findBySuggestionUseCase(sug.suggestion_id).collect {
                commentsResponse = it
            }
        }
    }

    fun onNewComment() {
        suggestion?.let { sug ->
            val comment = Comment(
                suggestion_id = sug.suggestion_id,
                user_id = currentUser?.uid ?: "",
                content = state.content
            )
            createComment(comment)
        }
    }
}