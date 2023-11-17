package com.aldeadavila.suggestionbox.domain.usecase.categories

import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository

class GetCategoryUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke() = categoriesRepository.getCategories()
}