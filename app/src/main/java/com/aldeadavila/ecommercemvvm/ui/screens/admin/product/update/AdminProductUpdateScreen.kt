package com.aldeadavila.suggestionbox.ui.screens.admin.product.update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.ui.components.DefaultTopBar
import com.aldeadavila.suggestionbox.ui.screens.admin.product.update.components.AdminProductUpdateContent
import com.aldeadavila.suggestionbox.ui.screens.admin.product.update.components.UpdateProduct
import com.aldeadavila.suggestionbox.ui.theme.Gray200

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
        containerColor = Gray200
    ) {
        AdminProductUpdateContent(paddingValues = it)
    }
    UpdateProduct()
}