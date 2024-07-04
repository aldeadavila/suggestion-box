package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Response<FirebaseUser>

    suspend fun signUp(user: User): Response<FirebaseUser>

    fun logout()
    suspend fun signInAnonymously(): Response<Boolean>
}