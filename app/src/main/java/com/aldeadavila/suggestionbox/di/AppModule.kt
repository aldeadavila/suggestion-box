package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.data.repository.AuthRepositoryImpl
import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.GetCurrentUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.GetSessionDataUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LoginUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LogoutUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.RegisterUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.SaveSessionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.UpdateSessionUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module

object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUserUseCase(repository),
        login = LoginUseCase(repository),
        register = RegisterUseCase(repository),
        saveSession = SaveSessionUseCase(repository),
        getSessionData = GetSessionDataUseCase(repository),
        logout = LogoutUseCase(repository),
        updateSession = UpdateSessionUseCase(repository)
    )
}