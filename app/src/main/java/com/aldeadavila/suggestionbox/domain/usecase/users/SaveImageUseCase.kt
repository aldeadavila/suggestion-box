package com.aldeadavila.suggestionbox.domain.usecase.users

import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import java.io.File
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(
    private val repository: UsersRepository
){
    suspend operator fun invoke(file: File, userId: String) = repository.saveImage(file, userId)
}