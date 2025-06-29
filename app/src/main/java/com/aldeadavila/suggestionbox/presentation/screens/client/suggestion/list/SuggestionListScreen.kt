package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.navigation.DetailsScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components.GetSuggestions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionListScreen(
    navHostController: NavHostController, 
    drawerState: DrawerState? = null,
    vm: SuggestionListViewModel = hiltViewModel()
) {
    vm.getSuggestions()

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Sugerencias",
                drawerState = drawerState
            )
        },
        floatingActionButton = {
            if (!vm.isAnonymous()) {
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 50.dp),
                    onClick = { navHostController.navigate(DetailsScreen.CreateSuggestion.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir sugerencia"
                    )
                }
            }
        }
    ) { paddingValues ->
         GetSuggestions(
            navHostController = navHostController,
            paddingValues = paddingValues
        )
    }
}