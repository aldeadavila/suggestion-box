package com.aldeadavila.suggestionbox.ui.screens.auth.register

data class RegisterState(
    val name:String = "",
    val lastname:String = "",
    val email:String = "",
    val phone:String = "",
    val password:String = "",
    val confirmPassword:String = ""
)
