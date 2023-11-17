package com.aldeadavila.suggestionbox.ui.screens.client.product.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.ui.screens.client.product.list.components.GetProducts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientProductListScreen(navHostController: NavHostController) {
    Scaffold {
        GetProducts(
            navHostController = navHostController,
            paddingValues = it
        )
    }
}