package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.data.datasource.LoginDataSource
import com.aldeadavila.suggestionbox.data.datasource.impl.LoginDataSourceImpl
import com.aldeadavila.suggestionbox.data.repository.LoginRepository
import com.aldeadavila.suggestionbox.data.repository.impl.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideLoginDataSource(
    ): LoginDataSource =
        LoginDataSourceImpl()

    @Provides
    fun provideLoginRepository(
        loginDataSource: LoginDataSource,
    ): LoginRepository =
        LoginRepositoryImpl(loginDataSource)

}