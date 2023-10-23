package com.aldeadavila.suggestionbox.domain.usecase


import com.aldeadavila.suggestionbox.domain.repository.LoginRepository
import com.aldeadavila.suggestionbox.util.Response

class LoginUseCase constructor(
    private val loginRepository: LoginRepository,
)  {

    suspend fun invoke(email: String, password: String): Response<Boolean> {
        return try {
            when (val response = loginRepository.firebaseSignInWithEmailAndPassword(email, password)) {
                is Response.Success -> response
                is Response.Failure -> response
                Response.Loading -> TODO()
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }



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