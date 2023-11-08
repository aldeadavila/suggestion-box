package com.aldeadavila.suggestionbox.presentation.profile.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.common.BottomNavigationItem
import com.aldeadavila.suggestionbox.presentation.common.BottomNavigationMenu
import com.aldeadavila.suggestionbox.util.Constants

@Composable
fun ProfileContent(
    padding: PaddingValues,
    navController: NavHostController,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column (
            modifier = Modifier
                .weight(1f)
        ){
            Text(
                text = Constants.WELCOME_MESSAGE,
                fontSize = 24.sp
            )
        }
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.PROFILE,
            navController = navController
        )
    }

}