package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Location
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
    Log.d("MAPS", "LocationListContent started")
    
    val context = LocalContext.current
    Box(Modifier.fillMaxSize()) {
        val initialCoordinates = LatLng(41.21850902356192, -6.619980581162994)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(initialCoordinates, 15f)
        }
        
        var uiSettings by remember {
            mutableStateOf(
                MapUiSettings(
                    zoomControlsEnabled = true,
                    myLocationButtonEnabled = true,
                    mapToolbarEnabled = true
                )
            )
        }
        
        // Verificar permisos de ubicación
        val hasLocationPermission = remember {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            )
        }
        
        var properties by remember {
            mutableStateOf(
                MapProperties(
                    mapType = MapType.NORMAL,
                    isMyLocationEnabled = hasLocationPermission.value
                )
            )
        }

        Log.d("MAPS", "Before GoogleMap composable. Location permission: ${hasLocationPermission.value}")
        
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 55.dp),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings,
            onMapLoaded = {
                Log.d("MAPS", "Map loaded successfully")
            }
        ) {
            Log.d("MAPS", "Inside GoogleMap content. Locations count: ${locations.size}")
            
            locations.forEach { location ->
                Log.d("MAPS", "Adding marker for location: ${location.name} at ${location.coordinates.latitude},${location.coordinates.longitude}")
                
                val uriHandler = LocalUriHandler.current

                MarkerInfoWindowContent(
                    state = MarkerState(position = Location.toLatLng(location.coordinates)),
                    title = location.name,
                    snippet = location.link,
                    icon = BitmapDescriptorFactory.fromResource(chooseIcon(location.type))
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
                            if (!location.link.isNullOrBlank()) {
                                Text(
                                    text = location.link,
                                    color = Color.Blue
                                )
                            }
                            Text(text = location.address)
                            Text(text = location.phone)
                        }
                    }
                }
            }
        }
        
        Log.d("MAPS", "After GoogleMap composable")

        ScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopEnd),
            cameraPositionState = cameraPositionState
        )

        DisappearingScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopStart),
            cameraPositionState = cameraPositionState
        )
    }
}

fun chooseIcon(type: String): Int {
    return when (type) {
        "Farmacia"-> R.drawable.marker_farmacia
        "Casa Rural" -> R.drawable.marker_casa_rural
        "Mirador"  -> R.drawable.marker_mirador
        "Restaurante" -> R.drawable.marker_restaurante
        "Iglesia" -> R.drawable.marker_iglesia
        "Informacion" -> R.drawable.marker_informacion
        "Supermercado" -> R.drawable.marker_supermercado
        else -> R.drawable.marker_casa_rural
    }
}


