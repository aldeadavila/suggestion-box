package com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCases
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientCommentDetailViewModel @Inject constructor(
    private val commentsUseCases: CommentsUseCases,
    private val savedStateHandle: SavedStateHandle,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var data = savedStateHandle.get<String>("comment")
    var comment = Comment.fromJson(data!!)

    var user by mutableStateOf<User?> (null)
        private set
    var deleteCommentResponse by mutableStateOf<Resource<Unit>?>(null)
        private set


    fun deleteComment(id: String) = viewModelScope.launch {
        deleteCommentResponse = Resource.Loading
        var result = commentsUseCases.deleteCommentUseCase(id)
        deleteCommentResponse = result
    }

    fun getEditable(idUser: String): Boolean {
        return idUser == user?.id
    }


}