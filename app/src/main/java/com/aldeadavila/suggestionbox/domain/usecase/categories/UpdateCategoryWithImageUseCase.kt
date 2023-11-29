package com.aldeadavila.suggestionbox.domain.usecase.categories

import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository
import java.io.File

class UpdateCategoryWithImageUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke(id: String, category: Category, file: File) = categoriesRepository.updateWithImage(id, category, file)
}