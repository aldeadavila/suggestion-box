package com.aldeadavila.suggestionbox.domain.usecase.auth

data class AuthUseCases(
    val getCurrentUser: GetCurrentUserUseCase,
    val login: LoginUseCase,
    val signUp: SignUpUseCase,
    val logout: LogoutUseCase,

)
