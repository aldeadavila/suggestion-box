package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components.SuggestionDetailContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun SuggestionDetailScreen(navHostController: NavHostController, suggestionParam: String) {

    Scaffold {
        SuggestionDetailContent(
            navHostController = navHostController,
        )
    }
}