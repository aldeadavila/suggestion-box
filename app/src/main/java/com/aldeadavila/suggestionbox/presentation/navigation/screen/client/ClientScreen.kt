package com.aldeadavila.suggestionbox.presentation.navigation.screen.client

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ClientScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object CategoryList: ClientScreen(
        route = "client/category/list",
        title = "Categorías",
        icon = Icons.Default.List
    )

    object ProductList: ClientScreen(
        route = "client/suggestion/list",
        title = "Sugerencias",
        icon = Icons.Default.ThumbUp
    )

    object Profile: ClientScreen(
        route = "client/profile",
        title = "Perfíl",
        icon = Icons.Default.Person
    )

}
