package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.components.SuggestionCreateContent
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.components.CreateClientSuggestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionCreateScreen(navHostController: NavHostController) {


    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Añade tu Sugerencia",
                upAvailable = true,
                navController = navHostController
            )
        }
    ) {
        SuggestionCreateContent(paddingValues = it)
    }
    CreateClientSuggestion()
}