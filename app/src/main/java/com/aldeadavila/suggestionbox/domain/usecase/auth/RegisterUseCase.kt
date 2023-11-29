package com.aldeadavila.suggestionbox.domain.usecase.auth

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: User) = authRepository.register(user)
}
