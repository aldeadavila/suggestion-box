package com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail.components.CommentDetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentDetailScreen(navHostController: NavHostController, commentParam: String) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Comentario", navController = navHostController, upAvailable = true
            )
        },
    ) {

        CommentDetailContent(
            navHostController = navHostController, paddingValues = it
        )
    }

}