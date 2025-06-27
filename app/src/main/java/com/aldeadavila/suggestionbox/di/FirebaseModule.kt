package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.core.Config.COMMENTS
import com.aldeadavila.suggestionbox.core.Config.LOCATIONS
import com.aldeadavila.suggestionbox.core.Config.NEWS
import com.aldeadavila.suggestionbox.core.Config.SUGGESTIONS
import com.aldeadavila.suggestionbox.core.Config.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    @Singleton
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    @Singleton
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(SUGGESTIONS)
    @Singleton
    fun provideStorageSuggestionsRef(storage: FirebaseStorage): StorageReference = storage.reference.child(SUGGESTIONS)

    @Provides
    @Named(SUGGESTIONS)
    @Singleton
    fun provideSuggestionsRef(db: FirebaseFirestore): CollectionReference = db.collection(SUGGESTIONS)

    @Provides
    @Named(LOCATIONS)
    @Singleton
    fun provideStorageLocationsRef(storage: FirebaseStorage): StorageReference = storage.reference.child(LOCATIONS)

    @Provides
    @Named(LOCATIONS)
    @Singleton
    fun provideLocationsRef(db: FirebaseFirestore): CollectionReference = db.collection(LOCATIONS)

    @Provides
    @Named(NEWS)
    @Singleton
    fun provideStorageNewsRef(storage: FirebaseStorage): StorageReference = storage.reference.child(NEWS)

    @Provides
    @Named(NEWS)
    @Singleton
    fun provideNewsRef(db: FirebaseFirestore): CollectionReference = db.collection(NEWS)

    @Provides
    @Named("Travels")
    @Singleton
    fun provideTravelsRef(db: FirebaseFirestore): CollectionReference = db.collection("travels")

    @Provides
    @Named(COMMENTS)
    @Singleton
    fun provideCommentsRef(db: FirebaseFirestore): CollectionReference = db.collection(COMMENTS)
} 