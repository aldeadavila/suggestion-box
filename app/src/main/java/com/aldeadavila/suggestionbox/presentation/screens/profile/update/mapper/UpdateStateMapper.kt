package com.aldeadavila.suggestionbox.presentation.screens.profile.update.mapper

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.presentation.screens.profile.update.ProfileUpdateState

fun ProfileUpdateState.toUser(): User {
    return User(
        name = name,
        lastname = lastname,
        phone = phone,
        image = image
    )
}