package com.aldeadavila.suggestionbox.presentation.screens.client.news.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.News
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    var newsResponse by mutableStateOf<Response<List<News>>?>(null)

    fun getNews() = viewModelScope.launch {
        newsResponse = Response.Loading
        newsUseCases.getNewsUseCase().collect { response ->
            newsResponse = response

        }
    }

    fun printTitle(title: String): String {
        if (title.length > 38) {
            return title.substring(0, 38) + "..."
        } else {
            return title
        }
    }
}