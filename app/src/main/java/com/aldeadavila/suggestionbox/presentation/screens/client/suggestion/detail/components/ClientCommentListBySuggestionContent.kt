package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbycategory.components.ClientSuggestionListByCategoryItem

@Composable
fun ClientCommentListBySuggestionContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    comments: List<Comment>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(items = comments) {
            ClientCommentListBySuggestionItem(navHostController = navHostController, comment = it)
        }
    }
    
}