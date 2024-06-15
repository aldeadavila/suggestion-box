package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.navigation.graph.DetailsScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components.GetSuggestions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSuggestionListScreen(navHostController: NavHostController) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 50.dp),
                onClick = { navHostController.navigate(DetailsScreen.CreateSuggestion.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "")
            }
        }
    ){
        GetSuggestions(
            navHostController = navHostController,
            paddingValues = it
        )
    }
}