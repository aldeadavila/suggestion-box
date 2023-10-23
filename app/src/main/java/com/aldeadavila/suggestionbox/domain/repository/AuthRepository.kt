package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response

typealias SignInResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>

interface AuthRepository {

    suspend fun firebaseSignInWithEmailAndPassword(email: Any?, password: Any?): SignInResponse
    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse

}