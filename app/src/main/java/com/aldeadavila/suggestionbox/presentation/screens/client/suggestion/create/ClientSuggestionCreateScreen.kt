package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.components.ClientSuggestionCreateContent
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.components.CreateClientSuggestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSuggestionCreateScreen(navHostController: NavHostController, categoryParam: String) {

    val name = Category.fromJson(categoryParam).name
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Añade tu ${name.dropLast(1)}",
                upAvailable = true,
                navController = navHostController
            )
        }
    ) {
        ClientSuggestionCreateContent(paddingValues = it)
    }
    CreateClientSuggestion()
}