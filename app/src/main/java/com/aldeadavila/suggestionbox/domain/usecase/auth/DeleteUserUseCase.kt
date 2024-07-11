package com.aldeadavila.suggestionbox.domain.usecase.auth

import com.aldeadavila.suggestionbox.domain.repository.AuthRepository

class DeleteUserUseCase constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.deleteAccount()
}