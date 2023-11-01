package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.AuthUser
import com.aldeadavila.suggestionbox.domain.model.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

typealias SignUpResponse = Resource<Boolean>
typealias SendEmailVerificationResponse = Resource<Boolean>

typealias SendPasswordResetEmailResponse = Resource<Boolean>
typealias ReloadUserResponse = Resource<Boolean>
typealias RevokeAccessResponse = Resource<Boolean>

interface FirebaseRepository {

    val currentUser: FirebaseUser?

    fun firebaseSignIn(user: AuthUser): Flow<Resource<String>>
    fun currentUserExist(): Flow<Boolean>
    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse
    suspend fun sendEmailVerification(): SendEmailVerificationResponse
    suspend fun reloadFirebaseUser(): ReloadUserResponse
    suspend fun revokeAccess(): RevokeAccessResponse
    suspend fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
    fun signOut()

}