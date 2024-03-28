package com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update.mapper.toComment
import com.aldeadavila.suggestionbox.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminCommentUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val commentsUseCase: CommentsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(AdminCommentUpdateState())
        private set

    var commentResponse by mutableStateOf<Resource<Comment>?>(null)
    val resultingActivityHandler = ResultingActivityHandler()

    val data = savedStateHandle.get<String>("comment")
    val comment = Comment.fromJson(data!!)

    init {
        state = state.copy(
            content = comment.content,
            idSuggestion = comment.idSuggestion,
            idUser = comment.idUser
        )
    }

    fun onContentInput(input: String) {
        state = state.copy(content = input)
    }

    fun updateComment() = viewModelScope.launch {

        commentResponse = Resource.Loading
        val result = commentsUseCase.updateCommentUseCase(

            comment.id ?: "",

            state.toComment()
        )

        commentResponse = result
    }
}