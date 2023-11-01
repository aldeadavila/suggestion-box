package com.aldeadavila.suggestionbox.presentation.forgotpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import com.aldeadavila.suggestionbox.domain.repository.SendPasswordResetEmailResponse
import com.aldeadavila.suggestionbox.domain.model.Resource.Loading
import com.aldeadavila.suggestionbox.domain.model.Resource.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repo: FirebaseRepository
): ViewModel() {
    var sendPasswordResetEmailResponse by mutableStateOf<SendPasswordResetEmailResponse>(Success(false))

    fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        sendPasswordResetEmailResponse = Loading()
        sendPasswordResetEmailResponse = repo.sendPasswordResetEmail(email)
    }
}