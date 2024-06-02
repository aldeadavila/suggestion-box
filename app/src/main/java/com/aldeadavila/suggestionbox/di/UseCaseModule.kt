package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import com.aldeadavila.suggestionbox.domain.usecase.categories.CategoriesUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.CreateCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.DeleteCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.GetCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.UpdateCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.UpdateCategoryWithImageUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.CommentsUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.CreateCommentUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.DeleteCommentUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.FindAllCommentsUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.FindBySuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.FindByUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.comments.UpdateCommentUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.CreateSuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.DeleteSuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.FindAllUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.FindByCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.UpdateSuggestionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.UpdateSuggestionWithImageUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.CreateUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.FindUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UpdateUserUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UpdateUserWithImageUseCase
import com.aldeadavila.suggestionbox.domain.usecase.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUsersUseCase(usersRepository: UsersRepository) = UsersUseCase(
        updateUser = UpdateUserUseCase(usersRepository),
        updateUserWithImageUseCase = UpdateUserWithImageUseCase(usersRepository),
        findUserUseCase = FindUserUseCase(usersRepository),
        createUser = CreateUserUseCase(usersRepository),
    )

    @Provides
    fun provideCategoriesUseCase(categoriesRepository: CategoriesRepository) = CategoriesUseCase(
        createCategory= CreateCategoryUseCase(categoriesRepository),
        getCategories = GetCategoryUseCase(categoriesRepository),
        updateCategory = UpdateCategoryUseCase(categoriesRepository),
        updateCategoryWithImage = UpdateCategoryWithImageUseCase(categoriesRepository),
        deleteCategoryUseCase = DeleteCategoryUseCase(categoriesRepository)
    )

    @Provides
    fun provideSuggestionsUseCase(suggestionRepository: SuggestionRepository) = SuggestionsUseCase(
        createSuggestionUseCase = CreateSuggestionUseCase(suggestionRepository),
        findAll = FindAllUseCase(suggestionRepository),
        findByCategory = FindByCategoryUseCase(suggestionRepository),
        updateSuggestion = UpdateSuggestionUseCase(suggestionRepository),
        updateSuggestionWithImage = UpdateSuggestionWithImageUseCase(suggestionRepository),
        deleteSuggestion = DeleteSuggestionUseCase(suggestionRepository)
    )

    @Provides
    fun provideCommentsUseCase(commentsRepository: CommentsRepository) = CommentsUseCase(
        createCommentUseCase = CreateCommentUseCase(commentsRepository),
        deleteCommentUseCase = DeleteCommentUseCase(commentsRepository),
        findAllCommentsUseCase = FindAllCommentsUseCase(commentsRepository),
        findBySuggestionUseCase = FindBySuggestionUseCase(commentsRepository),
        findByUserUseCase = FindByUserUseCase(commentsRepository),
        updateCommentUseCase = UpdateCommentUseCase(commentsRepository)
    )
}