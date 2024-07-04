package com.aldeadavila.suggestionbox.presentation.screens.profile.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases): ViewModel() {

    var userData by mutableStateOf(User())
        private set

    init {
        getUserById()
    }

    fun logout() =  authUseCases.logout()

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserByIdUseCase(authUseCases.getCurrentUser()!!.uid).collect() {
            userData = it
        }
    }

    fun isanonymous() = authUseCases.getCurrentUser()?.let { firebaseUser -> firebaseUser?.isAnonymous }

}