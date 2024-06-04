package com.aldeadavila.suggestionbox.domain.usecase.users

data class UsersUseCase(

    val getUserByIdUseCase: GetUserByIdUseCase,
    val updateUser: UpdateUserUseCase,
    val updateUserWithImageUseCase: UpdateUserWithImageUseCase,
    val createUser: CreateUserUseCase,
)
