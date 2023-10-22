package com.aldeadavila.suggestionbox.data.datasource.impl

import com.aldeadavila.suggestionbox.data.datasource.LoginDataSource
import com.aldeadavila.suggestionbox.util.State
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor() : LoginDataSource {

    override suspend fun loginWithCredential(authCredential: AuthCredential): State<FirebaseUser> {
        return try {
            val firebaseAuthInstance = FirebaseAuth.getInstance()
            firebaseAuthInstance.signInWithCredential(authCredential).await()
            //return firebaseAuthInstance.currentUser ?: throw FirebaseAuthException("", "")
            State.Success(firebaseAuthInstance.currentUser!!)
        } catch (exception: Exception) {
            State.Error(exception)
        }
    }
}