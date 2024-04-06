package com.aldeadavila.suggestionbox.domain.usecase.users

data class UsersUseCase(

    val findUserUseCase: FindUserUseCase,
    val updateUser: UpdateUserUseCase,
    val updateUserWithImageUseCase: UpdateUserWithImageUseCase
)
