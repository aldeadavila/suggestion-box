package com.aldeadavila.suggestionbox.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.DrawerState
import kotlinx.coroutines.launch

@Composable
fun DrawerButton(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    
    IconButton(
        onClick = {
            scope.launch {
                drawerState.open()
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Abrir menú",
            tint = Color.Black
        )
    }
} 