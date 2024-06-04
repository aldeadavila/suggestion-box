package com.aldeadavila.suggestionbox.domain.usecase.users

import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(idUser: String) = repository.getUserById(idUser)
}