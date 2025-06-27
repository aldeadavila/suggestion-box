package com.aldeadavila.suggestionbox.presentation.screens.client.travel.detail.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.TravelViewModel

@Composable
fun DeleteTravel(
    vm: TravelViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = vm.state.isLoading) {
        if (!vm.state.isLoading) {
            if (vm.state.error == null) {
               // Toast.makeText(context, "El viaje ha sido eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, vm.state.error, Toast.LENGTH_LONG).show()
            }
        }
    }
} 