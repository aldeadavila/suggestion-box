package com.aldeadavila.suggestionbox.presentation.screens.client.product.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.aldeadavila.suggestionbox.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var data = savedStateHandle.get<String>("product")
    var product = Product.fromJson(data!!)
    var listProductImage = listOf<String>(
        product.image1 ?: "",
        product.image2 ?: ""
    )

}