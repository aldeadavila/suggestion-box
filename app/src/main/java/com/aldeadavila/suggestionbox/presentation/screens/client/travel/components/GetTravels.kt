package com.aldeadavila.suggestionbox.presentation.screens.client.travel.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.TravelViewModel

@Composable
fun GetTravels(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    vm: TravelViewModel = hiltViewModel()
) {
    val state = vm.state
    
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.travels.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay viajes disponibles",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(state.travels) { travel ->
                TravelItem(travel = travel, navHostController = navHostController)
            }
        }
    }
} 