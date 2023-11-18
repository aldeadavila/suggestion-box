package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components.ClientSuggestionDetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSuggestionDetailScreen(navHostController: NavHostController, productParam: String) {

    Scaffold {
        ClientSuggestionDetailContent(it)
    }

}