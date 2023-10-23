package com.aldeadavila.suggestionbox.data.repository.impl

import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import com.aldeadavila.suggestionbox.domain.model.Response.Failure
import com.aldeadavila.suggestionbox.domain.model.Response.Success
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {


    override suspend fun firebaseSignInWithEmailAndPassword(
        email: Any?, password: Any?
    ) = try {
        auth.signInWithEmailAndPassword(email.toString(), password.toString()).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun sendPasswordResetEmail(email: String) = try {
        auth.sendPasswordResetEmail(email).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

}