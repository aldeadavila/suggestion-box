package com.aldeadavila.suggestionbox.presentation.screens.profile.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase): ViewModel() {

    var userData by mutableStateOf(User())
        private set

    init {
        getUserById()
    }

    fun logout() =  authUseCase.logout()

    private fun getUserById() = viewModelScope.launch {
        usersUseCase.getUserByIdUseCase(authUseCase.getCurrentUser()!!.uid).collect() {
            userData = it
        }
    }

}