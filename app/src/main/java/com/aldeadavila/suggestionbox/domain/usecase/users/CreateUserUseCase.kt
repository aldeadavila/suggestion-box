package com.aldeadavila.suggestionbox.domain.usecase.users

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(user: User) = repository.createUser(user)
}