package com.aldeadavila.suggestionbox.domain.usecase.auth

data class AuthUseCases(
    val getCurrentUser: GetCurrentUserUseCase,
    val login: LoginUseCase,
    val anonymous: AnonymousLoginUseCase,
    val signUp: SignUpUseCase,
    val logout: LogoutUseCase,

    )
