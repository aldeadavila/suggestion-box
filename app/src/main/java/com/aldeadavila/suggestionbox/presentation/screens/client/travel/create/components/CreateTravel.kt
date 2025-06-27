package com.aldeadavila.suggestionbox.presentation.screens.client.travel.create.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.create.TravelCreateViewModel

@Composable
fun CreateTravel(
    vm: TravelCreateViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = vm.state.isSuccess) {
        if (vm.state.isSuccess) {
            vm.resetState()
            Toast.makeText(context, "Viaje creado correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = vm.state.error) {
        if (vm.state.error != null) {
            Toast.makeText(context, vm.state.error, Toast.LENGTH_LONG).show()
            vm.resetState()
        }
    }
} 