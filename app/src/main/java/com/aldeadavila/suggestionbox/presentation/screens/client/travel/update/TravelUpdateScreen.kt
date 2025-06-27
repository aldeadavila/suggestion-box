package com.aldeadavila.suggestionbox.presentation.screens.client.travel.update

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Travel
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.update.components.TravelUpdateContent
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.update.components.UpdateTravel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TravelUpdateScreen(
    navController: NavHostController,
    travelParam: String
) {
    val travel = Travel.fromJson(travelParam)

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Actualizar Viaje",
                upAvailable = true,
                navController = navController
            )
        }
    ) {
        TravelUpdateContent(paddingValues = it, travel = travel)
    }
    UpdateTravel()
} 