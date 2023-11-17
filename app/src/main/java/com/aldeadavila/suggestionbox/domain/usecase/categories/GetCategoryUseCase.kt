package com.aldeadavila.suggestionbox.domain.usecase.categories

import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository

class GetCategoryUseCase(private val categoriesRepository: CategoriesRepository) {

    operator fun invoke() = categoriesRepository.getCategories()
}