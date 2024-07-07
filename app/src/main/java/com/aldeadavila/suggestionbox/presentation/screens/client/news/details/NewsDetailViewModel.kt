package com.aldeadavila.suggestionbox.presentation.screens.client.news.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.aldeadavila.suggestionbox.domain.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel()  {

    var data = savedStateHandle.get<String>("news")
    var news = News.fromJson(data!!)
}