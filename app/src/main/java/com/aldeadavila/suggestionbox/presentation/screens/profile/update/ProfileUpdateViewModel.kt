package com.aldeadavila.suggestionbox.presentation.screens.profile.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCase
import com.aldeadavila.suggestionbox.presentation.util.ComposeFileProvider
import com.aldeadavila.suggestionbox.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase,
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(ProfileUpdateState())

    //Arguments
    val data = savedStateHandle.get<String>("user")
    var user = User.fromJson(data!!)

    //imagenes
    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    init {
        state = state.copy(
            nickname = user.nickname,
            profileImagePathUrl = ""
        )
    }

    fun onUpdate() {
        val myUser = User (
            id = user.id,
            nickname = state.nickname,
            profileImagePath = ""
        )
        update(myUser)
    }

    fun updateUserSession(userResponse: User) = viewModelScope.launch {
        authUseCase.updateSession(userResponse)
    }

    fun update(user: User) = viewModelScope.launch {

        updateResponse = Response.Loading
        val result = usersUseCase.updateUser(user)
        updateResponse = result
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(
                context,
                result
            )
            state = state.copy(profileImagePathUrl = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(
                profileImagePathUrl = ComposeFileProvider.getPathFromBitmap(
                    context,
                    result
                )
            )
            file = File(state.profileImagePathUrl)
        }
    }

    fun onNicknameInput(input: String) {
        state = state.copy(nickname = input)
    }

    fun onImageInput(input: String) {
        state = state.copy(profileImagePathUrl = input)
    }

}