package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.WalkingRoute
import com.aldeadavila.suggestionbox.util.GpxParser
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WalkingRoutesViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _routes = mutableStateListOf<WalkingRoute>()
    val routes: List<WalkingRoute> get() = _routes
    
    // Lista de colores predefinidos para las rutas
    private val routeColors = listOf(
        0xFF0288D1.toInt(), // Azul
        0xFFD32F2F.toInt(), // Rojo
        0xFF388E3C.toInt(), // Verde
        0xFFFFA000.toInt(), // Ámbar
        0xFF7B1FA2.toInt(), // Púrpura
        0xFFE64A19.toInt(), // Naranja profundo
        0xFF00796B.toInt(), // Verde azulado
        0xFF5D4037.toInt(), // Marrón
        0xFF455A64.toInt(), // Azul grisáceo
        0xFF616161.toInt()  // Gris
    )
    
    var isLoading by mutableStateOf(false)
        private set
    
    var errorMessage by mutableStateOf<String?>(null)
        private set
    
    /**
     * Parsea un archivo GPX desde un string y añade la ruta resultante a la lista
     */
    fun parseGpxFromString(gpxContent: String, routeName: String = "", routeDescription: String = "") {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                withContext(Dispatchers.IO) {
                    val inputStream = ByteArrayInputStream(gpxContent.toByteArray())
                    val route = GpxParser.parse(inputStream, routeName, routeDescription)
                    
                    if (route.points.isNotEmpty()) {
                        // Verificar si ya existe una ruta con el mismo nombre
                        val existingRouteIndex = _routes.indexOfFirst { it.name == route.name }
                        
                        if (existingRouteIndex == -1) {
                            // Asignar un color diferente a la ruta
                            val routeWithColor = route.copy(
                                color = getNextColor()
                            )
                            _routes.add(routeWithColor)
                            Log.d("WalkingRoutesVM", "Ruta añadida: ${route.name} con ${route.points.size} puntos")
                        } else {
                            Log.d("WalkingRoutesVM", "La ruta ${route.name} ya existe, no se añadirá duplicada")
                        }
                    } else {
                        errorMessage = "No se pudieron extraer puntos del archivo GPX"
                    }
                }
            } catch (e: Exception) {
                errorMessage = "Error al procesar el archivo GPX: ${e.localizedMessage}"
                Log.e("WalkingRoutesVM", "Error parsing GPX", e)
            } finally {
                isLoading = false
            }
        }
    }
    
    /**
     * Obtiene el siguiente color de la lista de colores predefinidos
     */
    private fun getNextColor(): Int {
        val index = _routes.size % routeColors.size
        return routeColors[index]
    }
    
    /**
     * Parsea un archivo GPX desde un archivo en el sistema de archivos
     */
    fun parseGpxFromFile(file: File, routeName: String = "", routeDescription: String = "") {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                withContext(Dispatchers.IO) {
                    val inputStream = file.inputStream()
                    val route = GpxParser.parse(inputStream, routeName, routeDescription)
                    
                    if (route.points.isNotEmpty()) {
                        // Verificar si ya existe una ruta con el mismo nombre
                        val existingRouteIndex = _routes.indexOfFirst { it.name == route.name }
                        
                        if (existingRouteIndex == -1) {
                            // Asignar un color diferente a la ruta
                            val routeWithColor = route.copy(
                                color = getNextColor()
                            )
                            _routes.add(routeWithColor)
                        } else {
                            Log.d("WalkingRoutesVM", "La ruta ${route.name} ya existe, no se añadirá duplicada")
                        }
                    } else {
                        errorMessage = "No se pudieron extraer puntos del archivo GPX"
                    }
                }
            } catch (e: Exception) {
                errorMessage = "Error al procesar el archivo GPX: ${e.localizedMessage}"
                Log.e("WalkingRoutesVM", "Error parsing GPX from file", e)
            } finally {
                isLoading = false
            }
        }
    }
    
    /**
     * Parsea un archivo GPX desde un InputStream
     */
    fun parseGpxFromInputStream(inputStream: InputStream, routeName: String = "", routeDescription: String = "") {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                withContext(Dispatchers.IO) {
                    val route = GpxParser.parse(inputStream, routeName, routeDescription)
                    
                    if (route.points.isNotEmpty()) {
                        // Verificar si ya existe una ruta con el mismo nombre
                        val existingRouteIndex = _routes.indexOfFirst { it.name == route.name }
                        
                        if (existingRouteIndex == -1) {
                            // Asignar un color diferente a la ruta
                            val routeWithColor = route.copy(
                                color = getNextColor()
                            )
                            _routes.add(routeWithColor)
                        } else {
                            Log.d("WalkingRoutesVM", "La ruta ${route.name} ya existe, no se añadirá duplicada")
                        }
                    } else {
                        errorMessage = "No se pudieron extraer puntos del archivo GPX"
                    }
                }
            } catch (e: Exception) {
                errorMessage = "Error al procesar el archivo GPX: ${e.localizedMessage}"
                Log.e("WalkingRoutesVM", "Error parsing GPX from input stream", e)
            } finally {
                isLoading = false
            }
        }
    }
    
    /**
     * Añade una ruta manualmente
     */
    fun addRoute(route: WalkingRoute) {
        // Verificar si ya existe una ruta con el mismo nombre
        val existingRouteIndex = _routes.indexOfFirst { it.name == route.name }
        
        if (existingRouteIndex == -1) {
            // Asignar un color diferente a la ruta si no tiene uno específico
            val routeWithColor = if (route.color == 0xFF0288D1.toInt()) {
                route.copy(color = getNextColor())
            } else {
                route
            }
            _routes.add(routeWithColor)
        } else {
            Log.d("WalkingRoutesVM", "La ruta ${route.name} ya existe, no se añadirá duplicada")
        }
    }
    
    /**
     * Elimina una ruta por su ID
     */
    fun removeRoute(routeId: String) {
        _routes.removeIf { it.id == routeId }
    }
    
    /**
     * Actualiza la visibilidad de una ruta
     */
    fun updateRouteVisibility(routeId: String, isVisible: Boolean) {
        val index = _routes.indexOfFirst { it.id == routeId }
        if (index != -1) {
            val route = _routes[index]
            _routes[index] = route.copy(isVisible = isVisible)
        }
    }
    
    /**
     * Actualiza el color de una ruta
     */
    fun updateRouteColor(routeId: String, color: Int) {
        val index = _routes.indexOfFirst { it.id == routeId }
        if (index != -1) {
            val route = _routes[index]
            _routes[index] = route.copy(color = color)
        }
    }
    
    /**
     * Extrae las coordenadas del archivo GPX proporcionado
     */
    fun extractPointsFromPiconDelFelipeGpx(gpxContent: String): List<LatLng> {
        return try {
            val points = GpxParser.extractPointsFromGpxString(gpxContent)
            Log.d("WalkingRoutesVM", "Extracted ${points.size} points from Picón del Felipe GPX")
            points
        } catch (e: Exception) {
            Log.e("WalkingRoutesVM", "Error extracting points from Picón del Felipe GPX", e)
            emptyList()
        }
    }
    
    /**
     * Limpia todas las rutas
     */
    fun clearRoutes() {
        _routes.clear()
    }
} 