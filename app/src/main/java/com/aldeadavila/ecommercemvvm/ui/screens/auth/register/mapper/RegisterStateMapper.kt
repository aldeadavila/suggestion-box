package com.aldeadavila.suggestionbox.ui.screens.auth.register.mapper

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.ui.screens.auth.register.RegisterState

fun RegisterState.toUser(): User {
    return User(
        name = name,
        lastname = lastname,
        email = email,
        phone = phone,
        password = password
    )
}