package com.aldeadavila.suggestionbox.presentation.screens.auth.register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
    ) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    var errorMessage by mutableStateOf("")

    var registerResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun createUser() = viewModelScope.launch {
        user.user_id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.createUser(user)
    }
    fun signUp(user: User) = viewModelScope.launch {
        if (isValidateForm()) {
            registerResponse = Response.Loading
            val result = authUseCases.signUp(user)
            registerResponse = result
        }
    }

    fun onSignUp() {
        user.nickname = state.nickname
        user.email = state.email
        user.password = state.password
        signUp(user)
    }

    fun onNicknameInput(nickname: String) {
        state = state.copy(nickname = nickname)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(password: String) {
        state = state.copy(confirmPassword = password)
    }

    fun isValidateForm(): Boolean {

        if (state.nickname == "") {
            errorMessage = "Ingrese el apodo con el que quiere aparece en la app"
            return false
        } else if (state.email == "") {
            errorMessage = "Ingrese el email"
            return false
        } else if (state.password == "") {
            errorMessage = "Ingrese la contraseña"
            return false
        } else if (state.confirmPassword == "") {
            errorMessage = "Ingrese la confirmación del password"
            return false
        } else if (state.confirmPassword != state.password) {
            errorMessage = "Las contraseñas no coinciden"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            errorMessage = "El email no es válido"
            return false
        } else if (state.password.length < 6) {
            errorMessage = "La contraseña es menor de 6 caracteres"
            return false
        }

        return true
    }


}