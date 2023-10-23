package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.util.Response

typealias SignInResponse = Response<Boolean>

interface LoginRepository {


    suspend fun firebaseSignInWithEmailAndPassword(email: Any?, password: Any?): SignInResponse
}