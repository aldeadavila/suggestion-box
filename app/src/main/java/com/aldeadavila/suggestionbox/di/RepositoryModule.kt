package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import com.aldeadavila.suggestionbox.data.repository.FirebaseRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.SuggestionRespositoryImpl
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
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

   @Provides
   fun provideSuggestionRepository(
       suggestionList: CollectionReference
   ): SuggestionRepository = SuggestionRespositoryImpl(suggestionList)

}