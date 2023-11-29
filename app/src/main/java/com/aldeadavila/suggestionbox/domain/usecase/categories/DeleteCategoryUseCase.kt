package com.aldeadavila.suggestionbox.domain.usecase.categories

import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository

class DeleteCategoryUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend operator fun invoke(id: String) = categoriesRepository.delete(id)
}