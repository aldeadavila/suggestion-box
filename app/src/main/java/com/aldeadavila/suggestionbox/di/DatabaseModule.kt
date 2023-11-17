package com.aldeadavila.suggestionbox.di

import android.app.Application
import androidx.room.Room
import com.aldeadavila.suggestionbox.data.datasource.local.dao.CategoriesDao
import com.aldeadavila.suggestionbox.data.datasource.local.dao.ProductsDao
import com.aldeadavila.suggestionbox.data.datasource.local.db.EcommerceDB
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
    fun provideDatabase(app: Application): EcommerceDB =
        Room.databaseBuilder(app, EcommerceDB::class.java, "ecommerce_db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideCategoriesDao(db: EcommerceDB): CategoriesDao = db.categoriesDAO()

    @Provides
    fun provideProductssDao(db: EcommerceDB): ProductsDao = db.productsDAO()
}