package com.aldeadavila.suggestionbox.domain.usecase.auth

import com.aldeadavila.suggestionbox.domain.repository.AuthRepository

class GetSessionDataUseCase constructor(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.getSessionData()
}