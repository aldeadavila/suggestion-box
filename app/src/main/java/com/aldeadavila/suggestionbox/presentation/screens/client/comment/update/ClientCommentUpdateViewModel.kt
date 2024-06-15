package com.aldeadavila.suggestionbox.presentation.screens.client.comment.update

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCases
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.mapper.toComment
import com.aldeadavila.suggestionbox.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientCommentUpdateViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val commentsUseCases: CommentsUseCases,
    @ApplicationContext private val context: Context
) : ViewModel(){

    var state by mutableStateOf(ClientCommentUpdateState())
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
        val result = commentsUseCases.updateCommentUseCase(

            comment.id ?: "",

            state.toComment()
        )
        Log.d("ClientCommentUpdateViewModel", state.toComment().toString())
        commentResponse = result
    }
}