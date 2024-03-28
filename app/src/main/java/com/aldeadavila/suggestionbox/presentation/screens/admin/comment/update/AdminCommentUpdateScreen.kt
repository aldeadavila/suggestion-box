package com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update.components.AdminCommentUpdateContent
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.update.components.AdminUpdateComment

@Composable
fun AdminCommentUpdateScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Actualizar Comentario",
                upAvailable = true,
                navController = navHostController
            )
        },
        containerColor = Color.White
    ) {

        AdminCommentUpdateContent(paddingValues = it)
    }
    AdminUpdateComment()
}