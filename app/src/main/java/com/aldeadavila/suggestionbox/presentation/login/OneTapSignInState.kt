package com.aldeadavila.suggestionbox.presentation.login

import com.google.android.gms.auth.api.identity.BeginSignInResult


data class OneTapSignInState(
    val isLoading: Boolean? = false,
    val success: BeginSignInResult? = null,
    val error: String? = null
)
