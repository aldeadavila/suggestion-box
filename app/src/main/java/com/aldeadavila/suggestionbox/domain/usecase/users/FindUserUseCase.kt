package com.aldeadavila.suggestionbox.domain.usecase.users

import com.aldeadavila.suggestionbox.domain.repository.UsersRepository

class FindUserUseCase constructor(val usersRepository: UsersRepository) {

    suspend operator fun invoke(idUser: String) = usersRepository.findById(idUser)
}