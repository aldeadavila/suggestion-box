package com.aldeadavila.suggestionbox.presentation.screens.profile.update

data class ProfileUpdateState(
    val name:String = "",
    val lastname:String = "",
    val nickname: String = "",
    val phone:String = "",
    val image:String? = null
)
