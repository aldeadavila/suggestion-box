package com.aldeadavila.suggestionbox.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import com.aldeadavila.suggestionbox.domain.repository.SignInResponse
import com.aldeadavila.suggestionbox.domain.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signInResponse by mutableStateOf<SignInResponse>(Response.Success(false))
        private set

    fun signInWithEmailAndPassword(email: Any?, password: Any?) = viewModelScope.launch {
        signInResponse = Response.Loading
        signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
    }
}