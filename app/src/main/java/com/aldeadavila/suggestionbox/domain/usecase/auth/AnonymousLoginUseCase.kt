package com.aldeadavila.suggestionbox.domain.usecase.auth

import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import javax.inject.Inject

class AnonymousLoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke() = authRepository.signInAnonymously()
}