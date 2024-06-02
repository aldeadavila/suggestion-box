package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.core.Config
import com.aldeadavila.suggestionbox.data.datasource.remote.UsersRemoteDatasource
import com.aldeadavila.suggestionbox.data.repository.AuthRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.UsersRepositoryImpl
import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.GetCurrentUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.GetSessionDataUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LoginUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LogoutUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.SignUpUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.SaveSessionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.UpdateSessionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.CreateUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module

object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(Config.USERS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUserUseCase(repository),
        login = LoginUseCase(repository),
        signUp = SignUpUseCase(repository),
        saveSession = SaveSessionUseCase(repository),
        getSessionData = GetSessionDataUseCase(repository),
        logout = LogoutUseCase(repository),
        updateSession = UpdateSessionUseCase(repository)
    )
}