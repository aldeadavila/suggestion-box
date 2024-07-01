package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Location
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.widgets.DisappearingScaleBar
import com.google.maps.android.compose.widgets.ScaleBar

@Composable
fun LocationListContent(
    navHostController: NavHostController,
    locations: List<Location>
) {
    Box(Modifier.fillMaxSize()) {
        val initialCoordinates = LatLng(41.21850902356192, -6.619980581162994)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(initialCoordinates, 16f)
        }
        var uiSettings by remember {
            mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
        }
        var properties by remember {
            mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
        }


        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ) {
            locations.forEach { location ->
                MarkerInfoWindowContent(
                    state = MarkerState(position = Location.toLatLng(location.coordinates)),
                    title = location.name,
                    snippet = location.address,
                    //onInfoWindowClick =
                ) { marker ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(size = 60.dp)
                                .clip(shape = RoundedCornerShape(size = 10.dp)),
                            painter = painterResource(id = R.drawable.location_home),
                            contentDescription = location.address,
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(
                                text = marker.title ?: "",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = marker.snippet ?: "")
                        }
                    }
                }
            }
        }

        // This will be visible all the times
        ScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopEnd),
            cameraPositionState = cameraPositionState
        )

        // This will disappear after few seconds
        DisappearingScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopStart),
            cameraPositionState = cameraPositionState
        )


    /*    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(items = locations) { location ->
                LocationListItem(
                    navHostController = navHostController,
                    location = location
                )
            }
        }*/

    }
}


