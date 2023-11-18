package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.list

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
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.navigation.screen.admin.AdminCategoryScreen
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.list.components.DeleteSuggestion
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.list.components.GetSuggestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminsuggestionListScreen(navHostController: NavHostController, categoryParam: String) {

    val categoryParse = Category.fromJson(categoryParam).toJson()

    Scaffold (
        topBar = {
                 DefaultTopBar(
                     title = "Sugerencias",
                     navController  = navHostController,
                     upAvailable = true

                 )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 20.dp),
                onClick = { navHostController.navigate(route = AdminCategoryScreen.SuggestionCreate.passCategory(categoryParse)) },
                containerColor = Color.DarkGray
            )
            {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    ) {
        GetSuggestion(navHostController = navHostController, paddingValues = it)
    }
    DeleteSuggestion()
}