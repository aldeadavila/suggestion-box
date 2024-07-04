package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.core.Config.COMMENTS
import com.aldeadavila.suggestionbox.core.Config.LOCATIONS
import com.aldeadavila.suggestionbox.core.Config.SUGGESTIONS
import com.aldeadavila.suggestionbox.core.Config.USERS
import com.aldeadavila.suggestionbox.data.repository.AuthRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.CommentsRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.LocationsRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.SuggestionRepositoryImpl
import com.aldeadavila.suggestionbox.data.repository.UsersRepositoryImpl
import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository
import com.aldeadavila.suggestionbox.domain.repository.LocationsRepository
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import com.aldeadavila.suggestionbox.domain.usecase.auth.AnonymousLoginUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.domain.usecase.auth.GetCurrentUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LoginUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LogoutUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.SignUpUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCases
import com.aldeadavila.suggestionbox.domain.usecase.comments.CreateCommentUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.DeleteCommentUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.FindAllCommentsUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.FindBySuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.FindByUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.UpdateCommentUseCase
import com.aldeadavila.suggestionbox.domain.usecase.locations.GetLocationsUsecase
import com.aldeadavila.suggestionbox.domain.usecase.locations.LocationsUseCases
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.CreateSuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.DeleteSuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.FindByCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.GetSuggestionsByUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCases
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.UpdateSuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.getSuggestionsUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.CreateUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.GetUserByIdUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.SaveImageUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UpdateUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCases
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

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providesFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun providesStorageUsersRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(LOCATIONS)
    fun providesStorageLocationsRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(LOCATIONS)

    @Provides
    @Named(LOCATIONS)
    fun provideLocationsRef(db: FirebaseFirestore): CollectionReference = db.collection(LOCATIONS)

    @Provides
    @Named(SUGGESTIONS)
    fun providesStorageSuggestionsRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(SUGGESTIONS)

    @Provides
    @Named(SUGGESTIONS)
    fun provideSuggestionsRef(db: FirebaseFirestore): CollectionReference =
        db.collection(SUGGESTIONS)

    @Provides
    @Named(COMMENTS)
    fun provideCommentsRef(db: FirebaseFirestore): CollectionReference = db.collection(COMMENTS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideSuggestionsRepository(impl: SuggestionRepositoryImpl): SuggestionRepository = impl

    @Provides
    fun provideCommentsRepository(impl: CommentsRepositoryImpl): CommentsRepository = impl

    @Provides
    fun provideLocationsRepository(impl: LocationsRepositoryImpl): LocationsRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUserUseCase(repository),
        login = LoginUseCase(repository),
        signUp = SignUpUseCase(repository),
        logout = LogoutUseCase(repository),
        anonymous = AnonymousLoginUseCase(repository)
    )

    @Provides
    fun provideLocationsUseCases(repository: LocationsRepository) = LocationsUseCases(
        getLocationsUseCase = GetLocationsUsecase(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        createUser = CreateUserUseCase(repository),
        getUserByIdUseCase = GetUserByIdUseCase(repository),
        updateUser = UpdateUserUseCase(repository),
        saveImage = SaveImageUseCase(repository)
    )

    @Provides
    fun provideSuggestionsUseCases(repository: SuggestionRepository) = SuggestionsUseCases(
        createSuggestionUseCase = CreateSuggestionUseCase(repository),
        getSuggestionsUseCase = getSuggestionsUseCase(repository),
        findByCategory = FindByCategoryUseCase(repository),
        updateSuggestion = UpdateSuggestionUseCase(repository),
        deleteSuggestion = DeleteSuggestionUseCase(repository),
        getSuggestionsByUserUseCase = GetSuggestionsByUserUseCase(repository)
    )

    @Provides
    fun provideCommentsUseCases(repository: CommentsRepository) = CommentsUseCases(
        createCommentUseCase = CreateCommentUseCase(repository),
        deleteCommentUseCase = DeleteCommentUseCase(repository),
        findAllCommentsUseCase = FindAllCommentsUseCase(repository),
        findBySuggestionUseCase = FindBySuggestionUseCase(repository),
        findByUserUseCase = FindByUserUseCase(repository),
        updateCommentUseCase = UpdateCommentUseCase(repository),
    )
}