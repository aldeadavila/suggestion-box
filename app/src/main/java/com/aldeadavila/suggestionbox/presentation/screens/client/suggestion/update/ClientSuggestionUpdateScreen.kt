package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.components.UpdateSuggestion
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.components.ClientSuggestionUpdateContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSuggestionUpdateScreen(navHostController: NavHostController, suggestionParam: String) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Actualizar Sugerencia",
                upAvailable = true,
                navController = navHostController
            )
        },
        containerColor = Color.White
    ) {
        ClientSuggestionUpdateContent(paddingValues = it)
    }
    UpdateSuggestion()
}