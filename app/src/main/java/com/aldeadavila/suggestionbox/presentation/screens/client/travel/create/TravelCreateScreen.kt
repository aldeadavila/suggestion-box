package com.aldeadavila.suggestionbox.presentation.screens.client.travel.create

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.create.components.CreateTravel
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.create.components.TravelCreateContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TravelCreateScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo Viaje",
                upAvailable = true,
                navController = navHostController
            )
        }
    ) {
        TravelCreateContent(paddingValues = it)
    }
    CreateTravel()
} 