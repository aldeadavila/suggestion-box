package com.aldeadavila.suggestionbox.di

import com.aldeadavila.suggestionbox.domain.repository.AuthRepository
import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository
import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.GetSessionDataUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LoginUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.LogoutUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.RegisterUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.SaveSessionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.auth.UpdateSessionUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.CategoriesUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.CreateCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.DeleteCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.GetCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.UpdateCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.categories.UpdateCategoryWithImageUseCase
import com.aldeadavila.suggestionbox.domain.usecase.products.CreateProductUseCase
import com.aldeadavila.suggestionbox.domain.usecase.products.DeleteProductUseCase
import com.aldeadavila.suggestionbox.domain.usecase.products.FindAllUseCase
import com.aldeadavila.suggestionbox.domain.usecase.products.FindByCategoryUseCase
import com.aldeadavila.suggestionbox.domain.usecase.products.ProductsUseCase
import com.aldeadavila.suggestionbox.domain.usecase.products.UpdateProductUseCase
import com.aldeadavila.suggestionbox.domain.usecase.products.UpdateProductWithImageUseCase
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
    fun provideAuthUseCase(authRepository: AuthRepository) = AuthUseCase(
        login = LoginUseCase(authRepository),
        register = RegisterUseCase(authRepository),
        saveSession = SaveSessionUseCase(authRepository),
        getSessionData = GetSessionDataUseCase(authRepository),
        logout = LogoutUseCase(authRepository),
        updateSession = UpdateSessionUseCase(authRepository)
    )

    @Provides
    fun provideUsersUseCase(usersRepository: UsersRepository) = UsersUseCase(
        updateUser = UpdateUserUseCase(usersRepository),
        updateUserWithImageUseCase = UpdateUserWithImageUseCase(usersRepository)

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
    fun provideProductsUseCase(productsRepository: ProductsRepository) = ProductsUseCase(
        createProduct = CreateProductUseCase(productsRepository),
        findAll = FindAllUseCase(productsRepository),
        findByCategory = FindByCategoryUseCase(productsRepository),
        updateProduct = UpdateProductUseCase(productsRepository),
        updateProductWithImage = UpdateProductWithImageUseCase(productsRepository),
        deleteProduct = DeleteProductUseCase(productsRepository),

    )
}