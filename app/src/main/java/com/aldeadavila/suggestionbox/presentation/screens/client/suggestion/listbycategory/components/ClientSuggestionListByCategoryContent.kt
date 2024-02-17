package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbycategory.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Suggestion


@Composable
fun ClientSuggestionListByCategoryContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    suggestions: List<Suggestion>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(items = suggestions) {
            ClientSuggestionListByCategoryItem(navHostController = navHostController, suggestion = it)
        }
    }
}