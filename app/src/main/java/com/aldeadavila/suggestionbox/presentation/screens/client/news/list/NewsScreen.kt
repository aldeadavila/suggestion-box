package com.aldeadavila.suggestionbox.presentation.screens.client.news.list

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.news.list.components.GetNews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    navHostController: NavHostController,
    drawerState: DrawerState? = null,
    vm: NewsViewModel = hiltViewModel()
) {
    vm.getNews()
    
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Noticias",
                drawerState = drawerState
            )
        }
    ) { paddingValues ->
        GetNews(
            navHostController = navHostController,
            paddingValues = paddingValues
        )
    }
}