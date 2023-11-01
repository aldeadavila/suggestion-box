package com.aldeadavila.suggestionbox.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.AuthUser
import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import com.aldeadavila.suggestionbox.domain.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): ViewModel() {
    private val _signInState = Channel<FirebaseSignInState>()
    val signInState = _signInState.receiveAsFlow()

    val currentUserExist = firebaseRepository.currentUserExist()

    fun loginUser(user: AuthUser) = viewModelScope.launch {
        firebaseRepository.firebaseSignIn(user).collect {result ->
            when(result){
                is Resource.Loading -> {
                    _signInState.send(FirebaseSignInState(isLoading = true))
                }
                is Resource.Success -> {
                    _signInState.send(FirebaseSignInState(isSignedIn = "Signed In Successful"))

                }
                is Resource.Error -> {
                    _signInState.send(FirebaseSignInState(error = result.message))
                }
            }

        }
    }
}