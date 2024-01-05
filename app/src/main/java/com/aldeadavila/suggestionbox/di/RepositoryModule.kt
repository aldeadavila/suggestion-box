package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.data.repository.AuthRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.CategoriesRespositoryImpl
import com.aldeadavila.suggestionbox.data.repository.UsersRepositoryImpl
import com.aldeadavila.suggestionbox.data.datasource.local.AuthLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.local.CategoriesLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.local.CommentsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.local.SuggestionsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.AuthRemoteDatasource
import com.aldeadavila.suggestionbox.data.datasource.remote.CategoriesRemoteDatasource
import com.aldeadavila.suggestionbox.data.datasource.remote.CommentsRemoteDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.SuggestionsRemoteDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.UsersRemoteDatasource
import com.aldeadavila.suggestionbox.data.repository.CommentsRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.SuggestionRepositoryImpl
import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
        authRemoteDatasource: AuthRemoteDatasource,
        authLocalDataSource: AuthLocalDataSource
    ): AuthRepository = AuthRepositoryImpl(authRemoteDatasource, authLocalDataSource)

    @Provides
    fun provideUserRepository(
        usersRemoteDatasource: UsersRemoteDatasource
    ): UsersRepository = UsersRepositoryImpl(usersRemoteDatasource)

    @Provides
    fun provideCategoryRepository(
        categoriesRemoteDatasource: CategoriesRemoteDatasource,
        categoriesLocalDataSource: CategoriesLocalDataSource
    ): CategoriesRepository = CategoriesRespositoryImpl(categoriesRemoteDatasource, categoriesLocalDataSource)

    @Provides
    fun provideSuggestionsRepository(
        suggestionsRemoteDataSource: SuggestionsRemoteDataSource,
        suggestionsLocalDataSource: SuggestionsLocalDataSource
    ): SuggestionRepository = SuggestionRepositoryImpl(suggestionsRemoteDataSource, suggestionsLocalDataSource)

    @Provides
    fun provideCommentsRepository(
        commentsRemoteDataSource: CommentsRemoteDataSource,
        commentsLocalDataSource: CommentsLocalDataSource
    ): CommentsRepository = CommentsRepositoryImpl(commentsRemoteDataSource, commentsLocalDataSource)
}