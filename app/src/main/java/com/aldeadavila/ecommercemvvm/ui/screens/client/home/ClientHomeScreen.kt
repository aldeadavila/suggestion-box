package com.aldeadavila.suggestionbox.ui.screens.client.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aldeadavila.suggestionbox.ui.navigation.graph.client.ClientNavGraph
import com.aldeadavila.suggestionbox.ui.screens.client.home.components.ClientBottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientHomeScreen(navHostController: NavHostController = rememberNavController()
) {
    Scaffold (
        bottomBar = {
            ClientBottomBar(navHostController = navHostController)
        }
    ){ paddingValues ->
        ClientNavGraph(navController = navHostController)
    }

}