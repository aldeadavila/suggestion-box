package com.aldeadavila.suggestionbox.presentation.screens.client.news.list

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.news.list.components.GetNews

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    navHostController: NavHostController,
    vm: NewsViewModel = hiltViewModel()
) {

    vm.getNews()
    Scaffold {
        GetNews(navHostController = navHostController)
    }
}