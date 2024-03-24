package com.aldeadavila.suggestionbox.di

import android.app.Application
import androidx.room.Room
import com.aldeadavila.suggestionbox.data.datasource.local.dao.CategoriesDao
import com.aldeadavila.suggestionbox.data.datasource.local.dao.CommentsDao
import com.aldeadavila.suggestionbox.data.datasource.local.dao.SuggestionsDao
import com.aldeadavila.suggestionbox.data.datasource.local.db.SuggestionDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): SuggestionDB =
        Room.databaseBuilder(app, SuggestionDB::class.java, "suggestion_db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideCategoriesDao(db: SuggestionDB): CategoriesDao = db.categoriesDAO()

    @Provides
    fun provideSuggestionsDao(db: SuggestionDB): SuggestionsDao = db.suggestionsDAO()

    @Provides
    fun provideCommentsDao(db: SuggestionDB): CommentsDao = db.commentsDAO()
}