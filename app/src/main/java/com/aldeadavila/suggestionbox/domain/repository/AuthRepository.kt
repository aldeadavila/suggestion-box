package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response

typealias SignUpResponse = Response<Boolean>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>

interface AuthRepository {

    suspend fun firebaseSignInWithEmailAndPassword(email: Any?, password: Any?): SignInResponse
    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse
    suspend fun sendEmailVerification(): SendEmailVerificationResponse

}