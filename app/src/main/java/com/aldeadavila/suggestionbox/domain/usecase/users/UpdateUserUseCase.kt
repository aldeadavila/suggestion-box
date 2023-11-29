package com.aldeadavila.suggestionbox.domain.usecase.users

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository

class UpdateUserUseCase constructor(val usersRepository: UsersRepository) {

    suspend operator fun invoke(id: String, user: User) = usersRepository.update(id, user)
}