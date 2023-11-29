package com.aldeadavila.suggestionbox.domain.usecase.users

import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import java.io.File

class UpdateUserWithImageUseCase constructor(val usersRepository: UsersRepository) {

    suspend operator fun invoke(id: String, user: User, file: File) = usersRepository.updateWithImage(id, user, file)
}