package com.aldeadavila.suggestionbox.presentation.screens.admin.comment.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.admin.comment.detail.components.AdminCommentDetailContent

@Composable
fun AdminCommentDetailScreen(navHostController: NavHostController, commentParam: String) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Comentario", navController = navHostController, upAvailable = true
            )
        },
    ) {

        AdminCommentDetailContent(
            navHostController = navHostController, paddingValues = it
        )
    }

}