package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Suggestion

@Composable
fun SuggestionListContent(
    navHostController: NavHostController,

    suggestions: List<Suggestion>,
    user: String?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items = suggestions) {
            SuggestionListItem(
                navHostController = navHostController,
                suggestion = it
            )
        }
    }
}