package com.aldeadavila.suggestionbox.presentation.screens.client.comment.update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.admin.category.update.components.AdminCategoryUpdateContent
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.components.ClientCommentUpdateContent
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.update.components.UpdateComment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientCommentUpdateScreen(navHostController: NavHostController) {
    Scaffold (
        topBar = {
            DefaultTopBar(
                title = "Actualizar Comentario",
                upAvailable = true,
                navController = navHostController
            )
        },
        containerColor = Color.White
    ){

        ClientCommentUpdateContent(paddingValues = it)
    }
    UpdateComment()
}