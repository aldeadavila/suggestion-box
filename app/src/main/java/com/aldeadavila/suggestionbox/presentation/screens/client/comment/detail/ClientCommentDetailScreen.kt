package com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail.components.ClientCommentDetailContent
import com.aldeadavila.suggestionbox.presentation.screens.client.home.components.ClientBottomBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components.ClientSuggestionDetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientCommentDetailScreen(navHostController: NavHostController, commentParam: String) {

    Scaffold {
        ClientCommentDetailContent(
            navHostController = navHostController,
            paddingValues = it
        )
    }

}