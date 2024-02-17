package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.data.datasource.local.AuthLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.local.AuthLocalDataSourceImpl
import com.aldeadavila.suggestionbox.data.datasource.local.CategoriesLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.local.CategoriesLocalDataSourceImpl
import com.aldeadavila.suggestionbox.data.datasource.local.CommentsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.local.CommentsLocalDataSourceImpl
import com.aldeadavila.suggestionbox.data.datasource.local.SuggestionsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.local.SuggestionsLocalDataSourceImpl
import com.aldeadavila.suggestionbox.data.datasource.local.dao.CategoriesDao
import com.aldeadavila.suggestionbox.data.datasource.local.dao.CommentsDao
import com.aldeadavila.suggestionbox.data.datasource.local.dao.SuggestionsDao
import com.aldeadavila.suggestionbox.data.datasource.local.datastore.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    fun provideAuthLocalDataSource(authDataStore: AuthDataStore): AuthLocalDataSource = AuthLocalDataSourceImpl(authDataStore)

    @Provides
    fun provideCategoriesLocalDataSource(categoriesDao: CategoriesDao): CategoriesLocalDataSource = CategoriesLocalDataSourceImpl(categoriesDao)

    @Provides
    fun provideSuggestionsLocalDataSource(suggestionsDao: SuggestionsDao): SuggestionsLocalDataSource = SuggestionsLocalDataSourceImpl(suggestionsDao)

    @Provides
    fun provideCommentsLocalDataSource(commentsDao: CommentsDao): CommentsLocalDataSource = CommentsLocalDataSourceImpl(commentsDao)
}