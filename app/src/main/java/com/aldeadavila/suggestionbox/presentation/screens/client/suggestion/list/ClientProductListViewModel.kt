package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.usecase.suggestions.SuggestionsUseCase
import com.aldeadavila.suggestionbox.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProductListViewModel @Inject constructor(private val suggestionsUseCase: SuggestionsUseCase)  :ViewModel() {

    var productsResponse by mutableStateOf<Resource<List<Suggestion>>?>(null)
        private set

    init {
        getProducts()
    }
    fun getProducts() = viewModelScope.launch {
        productsResponse = Resource.Loading
        suggestionsUseCase.findAll().collect() {
            Log.d("ClientProductListViewModel", "Data: $it")
            productsResponse = it
        }
    }

}
