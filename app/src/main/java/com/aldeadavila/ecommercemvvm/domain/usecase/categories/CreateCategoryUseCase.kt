package com.aldeadavila.suggestionbox.domain.usecase.categories

import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository
import java.io.File

class CreateCategoryUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke(category: Category, file: File) = categoriesRepository.create(category, file)
}