package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
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
    when (val response = vm.locationsResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            LocationListContent(
                navHostController = navHostController,
                locations = response.data,
                walkingRoutesViewModel = walkingRoutesViewModel,
                paddingValues = paddingValues
            )
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {
        }
    }
}