package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.data.ImageRepositoryImpl
import com.aldeadavila.suggestionbox.repository.ImageRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideFirebaseStorage() = Firebase.storage

    @Provides
    fun provideFirebaseSFirestores() = Firebase.firestore

    @Provides
    fun provideImageRepository(
        storage: FirebaseStorage,
        db: FirebaseFirestore
    ): ImageRepository = ImageRepositoryImpl(
        storage = storage,
        db = db
    )
}