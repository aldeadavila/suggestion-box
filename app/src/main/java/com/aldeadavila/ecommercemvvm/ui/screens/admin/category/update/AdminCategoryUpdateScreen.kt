package com.aldeadavila.suggestionbox.ui.screens.admin.category.update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.ui.components.DefaultTopBar
import com.aldeadavila.suggestionbox.ui.screens.admin.category.update.components.AdminCategoryUpdateContent
import com.aldeadavila.suggestionbox.ui.screens.admin.category.update.components.UpdateCategory
import com.aldeadavila.suggestionbox.ui.theme.Gray200

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
        containerColor = Gray200
    ){

        AdminCategoryUpdateContent(paddingValues = it)
    }
    UpdateCategory()
}