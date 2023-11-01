package com.aldeadavila.suggestionbox.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Resource
import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import com.aldeadavila.suggestionbox.domain.repository.SendEmailVerificationResponse
import com.aldeadavila.suggestionbox.domain.repository.SignUpResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: FirebaseRepository
): ViewModel() {
    var signUpResource by mutableStateOf<SignUpResponse>(Resource.Success(false))
        private set
    var sendEmailVerificationResource by mutableStateOf<SendEmailVerificationResponse>(
        Resource.Success(
            false
        )
    )
        private set

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResource = Resource.Loading()
        signUpResource = repo.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResource = Resource.Loading()
        sendEmailVerificationResource = repo.sendEmailVerification()
    }
}