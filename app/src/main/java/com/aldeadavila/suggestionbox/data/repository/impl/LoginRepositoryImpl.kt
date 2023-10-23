package com.aldeadavila.suggestionbox.data.repository.impl

import com.aldeadavila.suggestionbox.domain.repository.LoginRepository
import com.aldeadavila.suggestionbox.util.Response
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {


    override suspend fun firebaseSignInWithEmailAndPassword(
        email: Any?, password: Any?
    ) = try {
        auth.signInWithEmailAndPassword(email.toString(), password.toString()).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }

}