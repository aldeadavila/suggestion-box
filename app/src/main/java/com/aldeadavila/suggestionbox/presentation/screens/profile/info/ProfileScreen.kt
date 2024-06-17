package com.aldeadavila.suggestionbox.presentation.screens.profile.info

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.components.ProfileContent

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    Scaffold {
        ProfileContent(paddingValues = it, navHostController = navHostController)
    }
}