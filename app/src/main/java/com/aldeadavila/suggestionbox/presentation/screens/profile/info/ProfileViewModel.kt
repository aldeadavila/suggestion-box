package com.aldeadavila.suggestionbox.presentation.screens.profile.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {

    var user by mutableStateOf<User?> (null)
        private set

    init {
        getSessionDate()
    }
    fun getSessionDate() = viewModelScope.launch {
        authUseCase.getSessionData().collect() { data ->
            user = data.user
        }
    }
    fun logout() = viewModelScope.launch {
        authUseCase.logout()
    }
}