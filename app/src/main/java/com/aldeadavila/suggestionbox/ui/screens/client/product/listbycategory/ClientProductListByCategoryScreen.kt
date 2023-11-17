package com.aldeadavila.suggestionbox.ui.screens.client.product.listbycategory

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.ui.components.DefaultTopBar
import com.aldeadavila.suggestionbox.ui.screens.client.product.listbycategory.components.GetProductsByCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientProductListByCategoryScreen(navHostController: NavHostController, categoryParam: String) {

    val categoryParse = Category.fromJson(categoryParam).toJson()

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Productos",
                navController = navHostController,
                upAvailable = true

            )
        },
    ) {
        GetProductsByCategory(
            navHostController = navHostController,
            paddingValues = it
        )
    }

}