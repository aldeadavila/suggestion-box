package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.LocationsListViewModel

@Composable
fun GetLocations(
    navHostController: NavHostController,
    vm: LocationsListViewModel = hiltViewModel()
) {

    when (val response = vm.locationsResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {

            LocationListContent(
                navHostController,
                locations = response.data
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