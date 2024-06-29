package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbyuser

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbyuser.components.GetSuggestionsByUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionListByUserScreen(
    navHostController: NavHostController
) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Mis sugerencias",
                navController = navHostController,
                upAvailable = true

            )
        }
    ) {
        GetSuggestionsByUser(
            navHostController = navHostController,
            paddingValues = it
        )
    }

}