package com.aldeadavila.suggestionbox.data.datasource

import com.aldeadavila.suggestionbox.util.Response
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface LoginDataSource {
    suspend fun loginWithCredential(authCredential: AuthCredential): Response<FirebaseUser>
}