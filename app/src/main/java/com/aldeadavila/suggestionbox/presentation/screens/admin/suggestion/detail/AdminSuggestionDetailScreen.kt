package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.detail.components.AdminSuggestionDetailContent

@Composable
fun AdminSuggestionDetailScreen(navHostController: NavHostController, suggestionParam: String) {

    Scaffold {
        AdminSuggestionDetailContent(
            navHostController = navHostController,
            paddingValues = it
        )
    }

}