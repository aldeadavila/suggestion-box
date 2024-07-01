package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Location
import com.aldeadavila.suggestionbox.presentation.navigation.DetailsScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.LocationsListViewModel

@Composable
fun LocationListItem(
    navHostController: NavHostController,
    location: Location,
    vm: LocationsListViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 10.dp,
                top = 5.dp
            )
            .height(110.dp)
            .clickable {
                navHostController.navigate(
                    route = DetailsScreen.DetailSuggestion.passSuggestion(
                        location.toJson()
                    )
                )
            }

    ) {
        /*Text(
            text = location.name,
            color = Color.Black,
            fontSize = 16.sp,
        )*/
    }
}