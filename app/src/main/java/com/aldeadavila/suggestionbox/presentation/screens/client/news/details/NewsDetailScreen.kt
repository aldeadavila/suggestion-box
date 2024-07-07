package com.aldeadavila.suggestionbox.presentation.screens.client.news.details

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.news.details.components.NewsDetailContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsDetailScreen(navHostController: NavHostController, newsParam: String) {

    Scaffold {
        NewsDetailContent()
    }
}