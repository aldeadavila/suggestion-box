package com.aldeadavila.suggestionbox.domain.usecase.auth

data class AuthUseCase(
    val getCurrentUser: GetCurrentUserUseCase,
    val login: LoginUseCase,
    val register: RegisterUseCase,
    val saveSession: SaveSessionUseCase,
    val getSessionData: GetSessionDataUseCase,
    val logout: LogoutUseCase,
    val updateSession: UpdateSessionUseCase
)
