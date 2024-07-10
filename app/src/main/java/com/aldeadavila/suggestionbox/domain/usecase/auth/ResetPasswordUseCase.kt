package com.aldeadavila.suggestionbox.domain.usecase.auth

import com.aldeadavila.suggestionbox.domain.repository.AuthRepository

class ResetPasswordUseCase constructor(private val authRepository: AuthRepository){
    suspend operator fun invoke(email: String) = authRepository.resetPassword(email)
}