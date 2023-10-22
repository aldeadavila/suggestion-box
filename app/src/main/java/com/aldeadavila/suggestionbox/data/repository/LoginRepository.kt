package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.util.State
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun loginWithCredential(authCredential: AuthCredential): State<FirebaseUser>
}