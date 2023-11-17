package com.aldeadavila.suggestionbox.ui.screens.admin.category.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.ui.components.DefaultTopBar
import com.aldeadavila.suggestionbox.ui.screens.admin.category.create.components.AdminCategoryCreateContent
import com.aldeadavila.suggestionbox.ui.screens.admin.category.create.components.CreateCategory
import com.aldeadavila.suggestionbox.ui.theme.Gray200

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
        containerColor = Gray200
    ){

        AdminCategoryCreateContent(paddingValues = it)
    }
    CreateCategory()
}