package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.core.Config.LOCATIONS
import com.aldeadavila.suggestionbox.core.Config.NEWS
import com.aldeadavila.suggestionbox.core.Config.SUGGESTIONS
import com.aldeadavila.suggestionbox.core.Config.USERS
import com.aldeadavila.suggestionbox.data.repository.*
import com.aldeadavila.suggestionbox.domain.repository.*
import com.aldeadavila.suggestionbox.domain.usecase.auth.*
import com.aldeadavila.suggestionbox.domain.usecase.comments.*
import com.aldeadavila.suggestionbox.domain.usecase.locations.*
import com.aldeadavila.suggestionbox.domain.usecase.news.*
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.*
import com.aldeadavila.suggestionbox.domain.usecase.users.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    @Singleton
    fun provideSuggestionsRepository(impl: SuggestionRepositoryImpl): SuggestionRepository = impl

    @Provides
    @Singleton
    fun provideCommentsRepository(impl: CommentsRepositoryImpl): CommentsRepository = impl

    @Provides
    @Singleton
    fun provideLocationsRepository(impl: LocationsRepositoryImpl): LocationsRepository = impl

    @Provides
    @Singleton
    fun provideNewsRepository(impl: NewsRepositoryImpl): NewsRepository = impl

    @Provides
    @Singleton
    fun provideTravelRepository(impl: TravelRepositoryImpl): TravelRepository = impl

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUserUseCase(repository),
        login = LoginUseCase(repository),
        signUp = SignUpUseCase(repository),
        logout = LogoutUseCase(repository),
        anonymous = AnonymousLoginUseCase(repository),
        resetPassword = ResetPasswordUseCase(repository),
        deleteUser = DeleteUserUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        createUser = CreateUserUseCase(repository),
        getUserByIdUseCase = GetUserByIdUseCase(repository),
        updateUser = UpdateUserUseCase(repository),
        saveImage = SaveImageUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideSuggestionsUseCases(repository: SuggestionRepository) = SuggestionsUseCases(
        createSuggestionUseCase = CreateSuggestionUseCase(repository),
        getSuggestionsUseCase = getSuggestionsUseCase(repository),
        getSuggestionsByUserUseCase = GetSuggestionsByUserUseCase(repository),
        updateSuggestion = UpdateSuggestionUseCase(repository),
        deleteSuggestion = DeleteSuggestionUseCase(repository),
        findByCategory = FindByCategoryUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideCommentsUseCases(repository: CommentsRepository) = CommentsUseCases(
        createCommentUseCase = CreateCommentUseCase(repository),
        deleteCommentUseCase = DeleteCommentUseCase(repository),
        findAllCommentsUseCase = FindAllCommentsUseCase(repository),
        findBySuggestionUseCase = FindBySuggestionUseCase(repository),
        findByUserUseCase = FindByUserUseCase(repository),
        updateCommentUseCase = UpdateCommentUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideLocationsUseCases(repository: LocationsRepository) = LocationsUseCases(
        getLocationsUseCase = GetLocationsUsecase(repository)
    )

    @Provides
    @Singleton
    fun provideNewsUseCases(repository: NewsRepository) = NewsUseCases(
        getNewsUseCase = GetNewsUsecase(repository)
    )
}