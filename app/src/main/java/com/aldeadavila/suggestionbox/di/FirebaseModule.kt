package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.data.repository.GoogleSignInRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.ImageRespositoryImpl
import com.aldeadavila.suggestionbox.domain.repository.GoogleSignInRepository
import com.aldeadavila.suggestionbox.domain.repository.ImageRepository
import com.aldeadavila.suggestionbox.domain.sdk.AuthService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun providesImageRepository(
        storage: FirebaseStorage,
        db: FirebaseFirestore
    ): ImageRepository = ImageRespositoryImpl(
        storage = storage,
        db = db
    )

    @Provides
    @Singleton
    fun provideSuggestionList(
        fireStore: FirebaseFirestore
    ) = fireStore.collection("suggestion")


    @Provides
    @Singleton
    fun provideAuthService(firebaseAuth: FirebaseAuth): AuthService {
        return AuthService(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesGoogleSignInRepository(
        auth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): GoogleSignInRepository {
        return GoogleSignInRepositoryImpl(
            firebaseAuth = auth,
            fireStore = fireStore
        )
    }
}