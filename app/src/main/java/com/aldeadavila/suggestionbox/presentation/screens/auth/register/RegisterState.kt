package com.aldeadavila.suggestionbox.presentation.screens.auth.register

data class RegisterState(
    val nickname:String = "",
    val email:String = "",
    val password:String = "",
    val confirmPassword:String = ""
)
