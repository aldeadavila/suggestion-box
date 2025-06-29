package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Location
import com.aldeadavila.suggestionbox.domain.model.WalkingRoute
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.WalkingRoutesViewModel
import com.aldeadavila.suggestionbox.presentation.util.WebViewActivity
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.widgets.DisappearingScaleBar
import com.google.maps.android.compose.widgets.ScaleBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun LocationListContent(
    navHostController: NavHostController,
    locations: List<Location>,
    walkingRoutesViewModel: WalkingRoutesViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    Log.d("MAPS", "LocationListContent started")
    
    val context = LocalContext.current
    
    // Procesar todos los archivos GPX disponibles en assets
    LaunchedEffect(Unit) {
        if (walkingRoutesViewModel.routes.isEmpty()) {
            try {
                // Limpiar rutas existentes para evitar duplicados
                walkingRoutesViewModel.clearRoutes()
                
                // Listar todos los archivos en la carpeta assets
                val assetFiles = withContext(Dispatchers.IO) {
                    context.assets.list("")?.filter { it.endsWith(".gpx") } ?: emptyList()
                }
                
                Log.d("MAPS", "Found ${assetFiles.size} GPX files in assets: $assetFiles")
                
                // Cargar cada archivo GPX
                assetFiles.forEach { fileName ->
                    try {
                        val gpxContent = context.assets.open(fileName).bufferedReader().use { it.readText() }
                        
                        // Extraer nombre de la ruta del nombre del archivo (sin extensión)
                        val routeName = fileName.substringBeforeLast(".").replace("-", " ").split(" ")
                            .joinToString(" ") { it.capitalize() }
                        
                        walkingRoutesViewModel.parseGpxFromString(
                            gpxContent, 
                            routeName,
                            "Ruta de senderismo: $routeName"
                        )
                        
                        Log.d("MAPS", "Loaded GPX file: $fileName as route: $routeName")
                    } catch (e: Exception) {
                        Log.e("MAPS", "Error loading GPX file: $fileName", e)
                    }
                }
                
                // Registrar información sobre las rutas cargadas
                Log.d("MAPS", "Total routes loaded: ${walkingRoutesViewModel.routes.size}")
                walkingRoutesViewModel.routes.forEachIndexed { index, route ->
                    Log.d("MAPS", "Route $index: '${route.name}' has ${route.points.size} points")
                }
            } catch (e: Exception) {
                Log.e("MAPS", "Error loading GPX files", e)
            }
        }
    }
    
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val initialCoordinates = LatLng(41.21850902356192, -6.619980581162994)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(initialCoordinates, 14f)
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
        
        // Estado para el tipo de mapa (iniciar directamente en vista satélite)
        var mapType by remember { mutableStateOf(MapType.SATELLITE) }
        
        var properties by remember {
            mutableStateOf(
                MapProperties(
                    mapType = mapType,
                    isMyLocationEnabled = hasLocationPermission.value
                )
            )
        }

        Log.d("MAPS", "Before GoogleMap composable. Location permission: ${hasLocationPermission.value}")
        
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings,
            onMapLoaded = {
                Log.d("MAPS", "Map loaded successfully")
            }
        ) {
            Log.d("MAPS", "Inside GoogleMap content. Locations count: ${locations.size}")
            
            // Dibujar las rutas de senderismo
            walkingRoutesViewModel.routes.forEach { route ->
                if (route.isVisible && route.points.isNotEmpty()) {
                    Log.d("MAPS", "Drawing route: ${route.name} with ${route.points.size} points and color: ${route.color}")
                    
                    Polyline(
                        points = route.points,
                        color = Color(route.color),
                        width = route.width,
                        onClick = {
                            Log.d("MAPS", "Route clicked: ${route.name}")
                            false
                        }
                    )
                    
                    // Añadir marcadores para inicio y fin de ruta
                    MarkerInfoWindowContent(
                        state = MarkerState(position = route.points.first()),
                        title = "Inicio: ${route.name}",
                        snippet = "Punto de partida",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    ) { marker ->
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = marker.title ?: "Inicio de ruta",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Distancia: ${String.format("%.2f", route.distance)} km")
                            Text(text = "Dificultad: ${route.difficulty}")
                            if (route.elevationGain > 0) {
                                Text(text = "Desnivel: ${route.elevationGain.toInt()} m")
                            }
                        }
                    }
                    
                    MarkerInfoWindowContent(
                        state = MarkerState(position = route.points.last()),
                        title = "Fin: ${route.name}",
                        snippet = "Punto de llegada",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    ) { marker ->
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = marker.title ?: "Fin de ruta",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Duración estimada: ${route.duration} min")
                        }
                    }
                }
            }
            
            // Mostrar los puntos de interés (locations)
            locations.forEach { location ->
                Log.d("MAPS", "Adding marker for location: ${location.name} at ${location.coordinates.latitude},${location.coordinates.longitude}")
                
                // Obtener el icono adecuado según el tipo de ubicación
                val iconResourceId = chooseIcon(location.type)
                Log.d("MAPS", "Location type: ${location.type}, using icon: $iconResourceId")
                
                // Usar BitmapDescriptorFactory.fromResource para los PNG
                val icon = if (iconResourceId.startsWith("marker_")) {
                    try {
                        val resourceId = context.resources.getIdentifier(
                            iconResourceId, "drawable", context.packageName
                        )
                        BitmapDescriptorFactory.fromResource(resourceId)
                    } catch (e: Exception) {
                        Log.e("MAPS", "Error loading marker icon: $iconResourceId", e)
                        getBitmapDescriptorFromVector(context, R.drawable.ic_location)
                    }
                } else {
                    getBitmapDescriptorFromVector(context, R.drawable.ic_location)
                }

                MarkerInfoWindowContent(
                    state = MarkerState(position = Location.toLatLng(location.coordinates)),
                    title = location.name,
                    snippet = location.link,
                    icon = icon
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
                                // Mostrar el enlace como texto
                                Text(
                                    text = location.link,
                                    color = Color.Gray,
                                    maxLines = 1
                                )
                                
                                // Añadir un botón explícito para abrir el enlace en el WebView
                                Button(
                                    onClick = {
                                        openInWebView(context, location.link)
                                    },
                                    modifier = Modifier.padding(top = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.OpenInBrowser,
                                        contentDescription = "Abrir enlace",
                                        modifier = Modifier.padding(end = 4.dp)
                                    )
                                    Text("Visitar web")
                                }
                            }
                            Text(text = location.address)
                            Text(text = location.phone)
                        }
                    }
                }
            }
        }
        
        Log.d("MAPS", "After GoogleMap composable")

        // Añadir el selector de rutas en la parte superior
        WalkingRoutesSelector(
            viewModel = walkingRoutesViewModel,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        // Botón para cambiar entre vista normal y satélite
        FloatingActionButton(
            onClick = {
                mapType = if (mapType == MapType.NORMAL) MapType.SATELLITE else MapType.NORMAL
                properties = properties.copy(mapType = mapType)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            Icon(
                imageVector = Icons.Default.Layers,
                contentDescription = "Cambiar tipo de mapa"
            )
        }

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

/**
 * Abre una URL en la actividad WebView
 */
fun openInWebView(context: Context, url: String) {
    try {
        // Asegurarse de que la URL tiene el formato correcto
        val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else {
            url
        }
        
        // Mostrar mensaje de depuración
        Toast.makeText(
            context,
            "Abriendo: $formattedUrl",
            Toast.LENGTH_SHORT
        ).show()
        
        // Lanzar la actividad WebView
        val intent = Intent(context, WebViewActivity::class.java).apply {
            putExtra("url", formattedUrl)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        
        Log.d("MAPS", "URL abierta en WebView: $formattedUrl")
    } catch (e: Exception) {
        Log.e("MAPS", "Error al abrir URL en WebView: $url", e)
        Toast.makeText(
            context,
            "Error al abrir el enlace: ${e.message}",
            Toast.LENGTH_LONG
        ).show()
    }
}

/**
 * Convierte un recurso drawable vectorial en un BitmapDescriptor
 * para usarlo como icono en los marcadores de Google Maps
 */
fun getBitmapDescriptorFromVector(context: android.content.Context, vectorResId: Int): BitmapDescriptor {
    // Obtener el drawable vectorial
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    
    // Crear un bitmap y un canvas para dibujar el vector
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    
    // Convertir el bitmap a BitmapDescriptor
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

/**
 * Selecciona el icono adecuado según el tipo de ubicación
 * Devuelve el nombre del recurso (no el ID)
 */
fun chooseIcon(type: String): String {
    return when (type.lowercase()) {
        "restaurant", "restaurante" -> "marker_restaurante"
        "hotel", "casa rural" -> "marker_casa_rural"
        "museum", "museo" -> "marker_informacion"
        "monument", "monumento" -> "marker_informacion"
        "church", "iglesia" -> "marker_iglesia"
        "mirador", "viewpoint" -> "marker_mirador"
        "farmacia", "pharmacy" -> "marker_farmacia"
        "supermercado", "supermarket" -> "marker_supermercado"
        else -> "marker_informacion"
    }
}

// Extensión para capitalizar la primera letra de cada palabra
private fun String.capitalize(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}


