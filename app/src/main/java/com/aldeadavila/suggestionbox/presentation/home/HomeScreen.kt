package com.aldeadavila.suggestionbox.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.common.BottomNavigationItem
import com.aldeadavila.suggestionbox.presentation.common.BottomNavigationMenu
import com.aldeadavila.suggestionbox.util.Constants

@Composable
fun HomeScreen(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column (
            modifier = Modifier
                .weight(1f)
        ){
            Text(
                text = "HOME SCREEN",
                fontSize = 24.sp
            )
        }
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.HOME,
            navController = navController
        )
    }
}