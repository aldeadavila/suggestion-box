package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.core.Config.BASE_URL
import com.aldeadavila.suggestionbox.data.datasource.local.datastore.AuthDataStore
import com.aldeadavila.suggestionbox.data.datasource.remote.service.AuthService
import com.aldeadavila.suggestionbox.data.datasource.remote.service.CategoryService
import com.aldeadavila.suggestionbox.data.datasource.remote.service.CommentService
import com.aldeadavila.suggestionbox.data.datasource.remote.service.SuggestionService
import com.aldeadavila.suggestionbox.data.datasource.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(dataStore: AuthDataStore) = OkHttpClient.Builder().addInterceptor {
        val token = runBlocking {
            dataStore.getData().first().token
        }

        val newRequest = it.request().newBuilder().addHeader("Authorization", token ?: "").build()

        it.proceed(newRequest)
    }.build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryService(retrofit: Retrofit): CategoryService {
        return retrofit.create(CategoryService::class.java)
    }

    @Provides
    @Singleton
    fun provideSuggestionsService(retrofit: Retrofit): SuggestionService {
        return retrofit.create(SuggestionService::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentsService(retrofit: Retrofit): CommentService {
        return retrofit.create(CommentService::class.java)
    }

}