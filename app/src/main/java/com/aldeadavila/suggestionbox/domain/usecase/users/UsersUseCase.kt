package com.aldeadavila.suggestionbox.domain.usecase.users

data class UsersUseCase(

    val getUserByIdUseCase: GetUserByIdUseCase,
    val updateUser: UpdateUserUseCase,
    val saveImage: SaveImageUseCase,
    val createUser: CreateUserUseCase,
)
