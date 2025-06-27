package com.aldeadavila.suggestionbox.presentation.screens.client.travel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.navigation.DetailsScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.components.GetTravels

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TravelScreen(
    navHostController: NavHostController,
    vm: TravelViewModel = hiltViewModel()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 50.dp),
                onClick = { navHostController.navigate(route = "travel/create") }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir viaje"
                )
            }
        }
    ) {
        GetTravels(navHostController = navHostController)
    }
} 