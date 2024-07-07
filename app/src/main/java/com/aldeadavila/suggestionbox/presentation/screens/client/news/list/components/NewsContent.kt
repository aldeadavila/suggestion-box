package com.aldeadavila.suggestionbox.presentation.screens.client.news.list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.News
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components.SuggestionListItem

@Composable
fun NewsContent(
    navHostController: NavHostController,
    news: List<News>
    ) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items = news) {
            NewsItem(
                navHostController = navHostController,
                news = it
            )
        }
    }

}