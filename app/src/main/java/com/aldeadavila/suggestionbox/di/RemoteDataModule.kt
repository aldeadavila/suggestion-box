package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.data.datasource.remote.AuthRemoteDatasource
import com.aldeadavila.suggestionbox.data.datasource.remote.CategoriesRemoteDatasource
import com.aldeadavila.suggestionbox.data.datasource.remote.UsersRemoteDatasource
import com.aldeadavila.suggestionbox.data.datasource.remote.AuthRemoteDatasourceImpl
import com.aldeadavila.suggestionbox.data.datasource.remote.CategoriesRemoteDatasourceImpl
import com.aldeadavila.suggestionbox.data.datasource.remote.SuggestionsRemoteDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.SuggestionsRemoteDataSourceImpl
import com.aldeadavila.suggestionbox.data.datasource.remote.UsersRemoteDatasourceImpl
import com.aldeadavila.suggestionbox.data.datasource.remote.service.AuthService
import com.aldeadavila.suggestionbox.data.datasource.remote.service.CategoryService
import com.aldeadavila.suggestionbox.data.datasource.remote.service.SuggestionService
import com.aldeadavila.suggestionbox.data.datasource.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDatasource = AuthRemoteDatasourceImpl(authService)
    @Provides
    fun provideUsersRemoteDataSource(userService: UserService): UsersRemoteDatasource = UsersRemoteDatasourceImpl(userService)
    @Provides
    fun provideCategoriesRemoteDataSource(categoryService: CategoryService): CategoriesRemoteDatasource = CategoriesRemoteDatasourceImpl(categoryService)
    @Provides
    fun provideProductsRemoteDataSource(suggestionService: SuggestionService): SuggestionsRemoteDataSource = SuggestionsRemoteDataSourceImpl(suggestionService)


}