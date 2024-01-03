package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbycategory

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
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCategoryScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.listbycategory.components.GetSuggestionsByCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientProductListByCategoryScreen(navHostController: NavHostController, categoryParam: String) {

    val categoryParse = Category.fromJson(categoryParam).toJson()
    val name = Category.fromJson(categoryParam).name

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = name,
                navController = navHostController,
                upAvailable = true

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 20.dp),
                onClick = { navHostController.navigate(route = ClientCategoryScreen.SuggestionCreate.passCategory(categoryParse)) },
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
        GetSuggestionsByCategory(
            navHostController = navHostController,
            paddingValues = it
        )
    }

}