package com.aldeadavila.suggestionbox.domain.usecase.auth

import com.aldeadavila.suggestionbox.domain.repository.AuthRepository

class LogoutUseCase constructor(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.logout()
}