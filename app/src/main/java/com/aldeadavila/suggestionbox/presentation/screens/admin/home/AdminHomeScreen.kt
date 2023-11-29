package com.aldeadavila.suggestionbox.presentation.screens.admin.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aldeadavila.suggestionbox.presentation.navigation.graph.admin.AdminNavGraph
import com.aldeadavila.suggestionbox.presentation.screens.admin.home.components.AdminBottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeScreen(navHostController: NavHostController = rememberNavController()) {
    Scaffold (
        bottomBar = { AdminBottomBar(navHostController = navHostController)}
    ){
        AdminNavGraph(navController = navHostController)
    }

}