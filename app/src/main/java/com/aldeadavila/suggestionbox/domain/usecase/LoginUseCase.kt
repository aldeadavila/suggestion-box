package com.aldeadavila.suggestionbox.domain.usecase

import com.aldeadavila.suggestionbox.base.Inputs
import com.aldeadavila.suggestionbox.base.UseCase
import com.aldeadavila.suggestionbox.data.repository.LoginRepository
import com.aldeadavila.suggestionbox.util.State
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

class LoginUseCase constructor(
    private val loginRepository: LoginRepository,
) : UseCase<LoginUseCase.Input?, FirebaseUser>() {

    override suspend fun invoke(input: Input?): State<FirebaseUser> {
        return try {
            when (val response = loginRepository.loginWithCredential(input!!.authCredential)) {
                is State.Success -> response
                is State.Error -> response
            }
        } catch (e: Exception) {
            State.Error(e)
        }
    }

    data class Input(
        val authCredential: AuthCredential,
    ) : Inputs
}

/*
override suspend fun invoke(input: Input): Flow<FirebaseUser> = flow {
    try {
        when (val response = loginRepository.loginWithCredential(input.authCredential)) {
            is State.Success -> emit(response.data)

            is State.Error -> response
        }
    } catch (e: Exception) {
        State.Error(e)
    }
}
*/