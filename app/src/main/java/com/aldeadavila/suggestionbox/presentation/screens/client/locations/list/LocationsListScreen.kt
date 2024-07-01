package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components.GetLocations

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationsListScreen(
    navHostController: NavHostController,
    vm: LocationsListViewModel = hiltViewModel()
) {

    vm.getLocations()
    Scaffold {

        GetLocations(navHostController = navHostController)
    }
}

