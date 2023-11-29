package com.aldeadavila.suggestionbox.presentation.screens.admin.category.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.create.components.AdminCategoryCreateContent
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.create.components.CreateCategory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCategoryCreateScreen(navHostController: NavHostController) {
    Scaffold (
        topBar = {
            DefaultTopBar(
                title = "Nueva Categoría",
                upAvailable = true,
                navController = navHostController
            )
        },
        containerColor = Color.Gray
    ){

        AdminCategoryCreateContent(paddingValues = it)
    }
    CreateCategory()
}