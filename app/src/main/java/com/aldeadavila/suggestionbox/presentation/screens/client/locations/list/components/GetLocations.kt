package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.LocationsListViewModel
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.WalkingRoutesViewModel

@Composable
fun GetLocations(
    navHostController: NavHostController,
    vm: LocationsListViewModel = hiltViewModel(),
    walkingRoutesViewModel: WalkingRoutesViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    val locations = when (val response = vm.locationsResponse) {
        is Response.Success -> response.data
        else -> emptyList()
    }

    val context = LocalContext.current
    if (vm.locationsResponse is Response.Failure) {
        LaunchedEffect(vm.locationsResponse) {
            (vm.locationsResponse as? Response.Failure)?.let {
                Toast.makeText(
                    context,
                    it.exception.message ?: "Error al cargar ubicaciones",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    Box {
        LocationListContent(
            navHostController = navHostController,
            locations = locations,
            walkingRoutesViewModel = walkingRoutesViewModel,
            paddingValues = paddingValues
        )
        if (vm.locationsResponse == Response.Loading) {
            ProgressBar()
        }
    }
}