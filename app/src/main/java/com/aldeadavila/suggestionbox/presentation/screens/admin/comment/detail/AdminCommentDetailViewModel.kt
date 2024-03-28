package com.aldeadavila.suggestionbox.presentation.screens.admin.comment.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminCommentDetailViewModel  @Inject constructor(
    private val commentsUseCase: CommentsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var data = savedStateHandle.get<String>("comment")
    var comment = Comment.fromJson(data!!)

    var user by mutableStateOf<User?> (null)
        private set
    var deleteCommentResponse by mutableStateOf<Resource<Unit>?>(null)
        private set

    init {
        getSessionDate()
    }

    fun deleteComment(id: String) = viewModelScope.launch {
        deleteCommentResponse = Resource.Loading
        var result = commentsUseCase.deleteCommentUseCase(id)
        deleteCommentResponse = result
    }

    fun getEditable(idUser: String): Boolean {
        return idUser == user?.id
    }

    private fun getSessionDate() = viewModelScope.launch {
        authUseCase.getSessionData().collect() { data ->
            user = data.user
        }
    }
}