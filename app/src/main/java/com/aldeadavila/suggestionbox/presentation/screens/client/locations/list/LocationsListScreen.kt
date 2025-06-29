package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components.GetLocations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsListScreen(
    navHostController: NavHostController,
    drawerState: DrawerState? = null,
    vm: LocationsListViewModel = hiltViewModel(),
    walkingRoutesViewModel: WalkingRoutesViewModel = hiltViewModel()
) {
    // Cargar las ubicaciones
    vm.getLocations()
    
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Mapa",
                drawerState = drawerState
            )
        }
    ) { paddingValues ->
        GetLocations(
            navHostController = navHostController,
            walkingRoutesViewModel = walkingRoutesViewModel,
            paddingValues = paddingValues
        )
    }
}

