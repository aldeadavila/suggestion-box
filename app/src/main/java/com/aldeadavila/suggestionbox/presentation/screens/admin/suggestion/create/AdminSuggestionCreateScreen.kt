package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.components.AdminSuggestionCreateContent
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.components.CreateSuggestion
import com.aldeadavila.suggestionbox.ui.theme.Gray200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminSuggestionCreateScreen(navHostController: NavHostController, categoryParam: String) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nueva Sugerencia",
                upAvailable = true,
                navController = navHostController
            )
        },
        containerColor = Gray200
    ) {
        AdminSuggestionCreateContent(paddingValues = it)
    }
    CreateSuggestion()
}