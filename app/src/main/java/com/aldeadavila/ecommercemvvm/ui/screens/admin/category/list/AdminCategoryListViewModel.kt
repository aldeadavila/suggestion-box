package com.aldeadavila.suggestionbox.ui.screens.admin.category.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.usecase.categories.CategoriesUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminCategoryListViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase): ViewModel() {

    var categoriesResponse by mutableStateOf<Resource<List<Category>>?>(null)
        private set

    var deleteCategoryResponse by mutableStateOf<Resource<Unit>?>(null)
        private set

    init {
        getCategories()
    }
    fun getCategories() = viewModelScope.launch {
        categoriesResponse = Resource.Loading
        categoriesUseCase.getCategories().collect() {data ->
            categoriesResponse = data
        }
    }

    fun deleteCategory(id: String) = viewModelScope.launch {
        deleteCategoryResponse = Resource.Loading
        val result = categoriesUseCase.deleteCategoryUseCase(id)
        deleteCategoryResponse = result
    }
}