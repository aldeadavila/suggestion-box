package com.aldeadavila.suggestionbox.domain.usecase.auth

import com.aldeadavila.suggestionbox.domain.model.AuthResponse
import com.aldeadavila.suggestionbox.domain.repository.AuthRepository

class SaveSessionUseCase constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(authResponse: AuthResponse) = authRepository.saveSession(authResponse)
}