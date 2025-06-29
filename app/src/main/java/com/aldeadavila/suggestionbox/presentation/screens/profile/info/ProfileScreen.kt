package com.aldeadavila.suggestionbox.presentation.screens.profile.info

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.components.ProfileContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    drawerState: DrawerState? = null
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Perfil",
                drawerState = drawerState
            )
        }
    ) {
        ProfileContent(paddingValues = it, navHostController = navHostController)
    }
}