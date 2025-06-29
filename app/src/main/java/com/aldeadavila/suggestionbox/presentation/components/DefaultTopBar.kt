package com.aldeadavila.suggestionbox.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    navController: NavHostController? = null,
    upAvailable: Boolean = false,
    drawerState: DrawerState? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 19.sp
            )
        },
        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = {
                    navController?.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.Black
                    )
                }
            } else if (drawerState != null) {
                // Si no hay botón de volver pero sí hay drawerState, mostrar el botón del menú
                DrawerButton(drawerState = drawerState)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
        )
    )
}