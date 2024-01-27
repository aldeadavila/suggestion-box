package com.aldeadavila.suggestionbox.presentation.screens.auth.register.mapper

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.presentation.screens.auth.register.RegisterState

fun RegisterState.toUser(): User {
    return User(
        name = name,
        lastname = lastname,
        nickname = nickname,
        email = email,
        phone = phone,
        password = password
    )
}