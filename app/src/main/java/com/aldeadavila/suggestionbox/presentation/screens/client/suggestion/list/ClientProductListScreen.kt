package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components.GetProducts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientProductListScreen(navHostController: NavHostController) {
    Scaffold {
        GetProducts(
            navHostController = navHostController,
            paddingValues = it
        )
    }
}