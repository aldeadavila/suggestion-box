package com.aldeadavila.suggestionbox.ui.screens.admin.product.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.domain.usecase.products.ProductsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminProductListViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var data = savedStateHandle.get<String>("category")
    var category = Category.fromJson(data!!)

    var productResponse by mutableStateOf<Resource<List<Product>>?>(null)
        private set

    var productDeleteResponse by mutableStateOf<Resource<Unit>?>(null)
        private set

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        productResponse = Resource.Loading
        productsUseCase.findByCategory(category.id!!).collect {
            productResponse = it
        }
    }

    fun deleteProduct(id: String) = viewModelScope.launch {
        productDeleteResponse = Resource.Loading
        val result = productsUseCase.deleteProduct(id)
        productDeleteResponse = result
    }
}