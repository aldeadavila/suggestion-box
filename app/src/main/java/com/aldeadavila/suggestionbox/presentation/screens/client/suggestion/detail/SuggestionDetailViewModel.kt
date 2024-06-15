package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCases
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCases
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.create.ClientCommentCreateState
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.create.mapper.toComment
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components.GetUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionDetailViewModel @Inject constructor(
    private val commentsUseCases: CommentsUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var data = savedStateHandle.get<String>("suggestion")
    var suggestion = Suggestion.fromJson(data!!)
    var listSuggestionImage = listOf<String>(
        suggestion.images[0],
        suggestion.images[1]
    )
    var commentsResponse by mutableStateOf<Resource<List<Comment>>?>(null)
    var commentResponse by mutableStateOf<Resource<Comment>?>(null)
    var state by mutableStateOf(ClientCommentCreateState())
    var stateUser by mutableStateOf(GetUserState())

    var user by mutableStateOf<User?>(null)
        private set

    var errorMessage by mutableStateOf("")

    init {
       // getComments()

    }

    fun createComment() = viewModelScope.launch {
        if (isValidateForm()) {
            commentResponse = Resource.Loading
            val result = commentsUseCases.createCommentUseCase(state.toComment())
            commentResponse = result
            clearComment()
        }
    }

    fun onCommentContentInput(input: String) {
        state = state.copy(content = input)
    }

    fun isFromMe(idUser: String): Boolean {
        val isMine = user?.id == idUser
        Log.d("ISFROMME", isMine.toString())
        return user?.id == idUser
    }

    fun isValidateForm(): Boolean {
        if (state.content == "") {
            errorMessage = "Los comentarios no pueden estar vacíos"
            return false
        }
        return true
    }

    /*fun getOwnerData(id: String) = viewModelScope.launch {

       when (val result = userUseCase.getUserByIdUseCase(suggestion.idUser)) {
            is Resource.Succes -> {
                println("Success: ${result.data}")
                stateUser = stateUser.copy(
                    nickname = result.data.nickname,
                    image =  result.data.profileImagePathUrl
                )
            }
            is Resource.Failure-> {
                println("Error: ${result.message}")
            }

            Resource.Loading -> TODO()
        }

    }*/

    private fun clearComment() {
        state = state.copy(content = "")
    }

    private fun getComments() = viewModelScope.launch {
        commentsResponse = Resource.Loading
        commentsUseCases.findBySuggestionUseCase(suggestion.user_id!!).collect {
            commentsResponse = it
        }
    }



}