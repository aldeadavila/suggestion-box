package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.ClientSuggestionListViewModel

@Composable
fun ClientSuggestionListContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    suggestions: List<Suggestion>,
    user: User?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(items = suggestions) {
            ClientSuggestionListItem(
                navHostController = navHostController,
                suggestion = it,
                user = user
            )
        }
    }
}