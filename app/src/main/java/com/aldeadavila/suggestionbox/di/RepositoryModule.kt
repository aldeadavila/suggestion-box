package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import com.aldeadavila.suggestionbox.data.repository.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideLoginRepository(
        auth: FirebaseAuth,
    ): FirebaseRepository =
        FirebaseRepositoryImpl(auth)

}