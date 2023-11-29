package com.aldeadavila.suggestionbox.domain.usecase.categories

import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository

class UpdateCategoryUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke(id: String, category: Category) = categoriesRepository.update(
        id,
        category
    )
}