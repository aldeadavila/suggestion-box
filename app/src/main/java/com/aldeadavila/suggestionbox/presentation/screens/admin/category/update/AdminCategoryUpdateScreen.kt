package com.aldeadavila.suggestionbox.presentation.screens.admin.category.update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.update.components.AdminCategoryUpdateContent
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.update.components.UpdateCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCategoryUpdateScreen(navHostController: NavHostController) {
    Scaffold (
        topBar = {
            DefaultTopBar(
                title = "Actualizar Categoría",
                upAvailable = true,
                navController = navHostController
            )
        },
        containerColor = Color.Gray
    ){

        AdminCategoryUpdateContent(paddingValues = it)
    }
    UpdateCategory()
}