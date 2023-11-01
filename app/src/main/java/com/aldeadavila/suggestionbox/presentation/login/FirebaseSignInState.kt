package com.aldeadavila.suggestionbox.presentation.login

data class FirebaseSignInState(
    var isLoading: Boolean = false,
    var isSignedIn: String? = null,
    val error: String? = null
)
