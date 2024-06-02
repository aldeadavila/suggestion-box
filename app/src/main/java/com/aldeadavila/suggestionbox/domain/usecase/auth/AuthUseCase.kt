package com.aldeadavila.suggestionbox.domain.usecase.auth

data class AuthUseCase(
    val getCurrentUser: GetCurrentUserUseCase,
    val login: LoginUseCase,
    val signUp: SignUpUseCase,
    val saveSession: SaveSessionUseCase,
    val getSessionData: GetSessionDataUseCase,
    val logout: LogoutUseCase,
    val updateSession: UpdateSessionUseCase
)
