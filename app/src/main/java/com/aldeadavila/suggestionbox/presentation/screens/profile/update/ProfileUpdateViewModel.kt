package com.aldeadavila.suggestionbox.presentation.screens.profile.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.presentation.screens.profile.update.mapper.toUser
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

    var updateResponse by mutableStateOf<Resource<User>?>(null)
        private set

    init {
        state = state.copy(
            name = user.name,
            lastname = user.lastname,
            nickname = user.nickname,
            phone = user.phone,
            image = user.image
        )
    }

    fun updateUsersWithImage() = viewModelScope.launch {
        updateResponse = Resource.Loading
        val result = usersUseCase.updateUserWithImageUseCase(
            user.id ?: "",
            state.toUser(),
            file!!
        )
        updateResponse = result
    }

    fun onUpdate() {
        if (file != null) {
            updateUsersWithImage()
        } else {
            update()
        }
    }

    fun updateUserSession(userResponse: User) = viewModelScope.launch {
        authUseCase.updateSession(userResponse)
    }

    fun update() = viewModelScope.launch {

        updateResponse = Resource.Loading
        val result = usersUseCase.updateUser(
            user.id ?: "",
            state.toUser()
        )
        updateResponse = result
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(
                context,
                result
            )
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(
                image = ComposeFileProvider.getPathFromBitmap(
                    context,
                    result
                )
            )
            file = File(state.image)
        }
    }

    fun onNameInput(input: String) {
        state = state.copy(name = input)
    }

    fun onLastNameInput(input: String) {
        state = state.copy(lastname = input)
    }

    fun onNicknameInput(input: String) {
        state = state.copy(nickname = input)
    }

    fun onImageInput(input: String) {
        state = state.copy(image = input)
    }

    fun onPhoneInput(input: String) {
        state = state.copy(phone = input)
    }

}