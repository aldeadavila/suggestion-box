package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response
import com.google.firebase.auth.FirebaseUser

typealias SignUpResponse = Response<Boolean>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>
typealias ReloadUserResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun firebaseSignInWithEmailAndPassword(email: Any?, password: Any?): SignInResponse
    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse
    suspend fun sendEmailVerification(): SendEmailVerificationResponse
    suspend fun reloadFirebaseUser(): ReloadUserResponse
    suspend fun revokeAccess(): RevokeAccessResponse
    fun signOut()

}