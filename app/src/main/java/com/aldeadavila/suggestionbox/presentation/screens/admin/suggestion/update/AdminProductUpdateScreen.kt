package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.components.AdminProductUpdateContent
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.update.components.UpdateProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProductUpdateScreen(navHostController: NavHostController, productParam: String) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Actualizar Producto",
                upAvailable = true,
                navController = navHostController
            )
        },
        containerColor = Color.Gray
    ) {
        AdminProductUpdateContent(paddingValues = it)
    }
    UpdateProduct()
}