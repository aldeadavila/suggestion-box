package com.aldeadavila.suggestionbox.util

import android.content.Context
import android.util.Log
import com.aldeadavila.suggestionbox.domain.model.WalkingRoute
import com.google.android.gms.maps.model.LatLng
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.util.UUID

/**
 * Clase utilitaria para procesar archivos GPX
 */
object GpxParser {
    
    private const val TAG = "GpxParser"
    
    /**
     * Parsea un archivo GPX desde un InputStream y devuelve una ruta de senderismo
     */
    fun parse(inputStream: InputStream, routeName: String = "", routeDescription: String = ""): WalkingRoute {
        val points = mutableListOf<LatLng>()
        val elevations = mutableListOf<Double>()
        var name = routeName
        var description = routeDescription
        
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            
            var eventType = parser.eventType
            var inTrackPoint = false
            var lat = 0.0
            var lon = 0.0
            var ele = 0.0
            
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (tagName) {
                            "name" -> {
                                // Solo captura el nombre si no se proporcionó uno
                                if (name.isEmpty() && parser.next() == XmlPullParser.TEXT) {
                                    name = parser.text
                                }
                            }
                            "desc" -> {
                                // Solo captura la descripción si no se proporcionó una
                                if (description.isEmpty() && parser.next() == XmlPullParser.TEXT) {
                                    description = parser.text
                                }
                            }
                            "trkpt" -> {
                                inTrackPoint = true
                                lat = parser.getAttributeValue(null, "lat").toDouble()
                                lon = parser.getAttributeValue(null, "lon").toDouble()
                                ele = 0.0 // Reiniciar la elevación para este punto
                            }
                            "ele" -> {
                                if (inTrackPoint && parser.next() == XmlPullParser.TEXT) {
                                    try {
                                        ele = parser.text.toDouble()
                                    } catch (e: NumberFormatException) {
                                        Log.e(TAG, "Error parsing elevation: ${parser.text}", e)
                                    }
                                }
                            }
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (tagName == "trkpt" && inTrackPoint) {
                            points.add(LatLng(lat, lon))
                            elevations.add(ele)
                            inTrackPoint = false
                        }
                    }
                }
                eventType = parser.next()
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing GPX file", e)
        }
        
        val distance = WalkingRoute.calculateDistance(points)
        val duration = estimateDuration(distance)
        val difficulty = estimateDifficulty(distance, points)
        val elevationGain = calculateElevationGain(elevations)
        
        return WalkingRoute(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description,
            points = points,
            distance = distance,
            duration = duration,
            difficulty = difficulty,
            elevationGain = elevationGain
        )
    }
    
    /**
     * Parsea un archivo GPX desde un recurso raw y devuelve una ruta de senderismo
     */
    fun parseFromRaw(context: Context, rawResourceId: Int, routeName: String = "", routeDescription: String = ""): WalkingRoute {
        val inputStream = context.resources.openRawResource(rawResourceId)
        return parse(inputStream, routeName, routeDescription)
    }
    
    /**
     * Calcula el desnivel positivo acumulado en metros
     */
    private fun calculateElevationGain(elevations: List<Double>): Double {
        if (elevations.size < 2) return 0.0
        
        var totalGain = 0.0
        for (i in 1 until elevations.size) {
            val diff = elevations[i] - elevations[i-1]
            if (diff > 0) {
                totalGain += diff
            }
        }
        
        return totalGain
    }
    
    /**
     * Estima la duración de la ruta en minutos basada en la distancia
     * Asume una velocidad promedio de caminata de 4 km/h
     */
    private fun estimateDuration(distanceKm: Double): Int {
        val walkingSpeedKmPerHour = 4.0
        val hoursToComplete = distanceKm / walkingSpeedKmPerHour
        return (hoursToComplete * 60).toInt()
    }
    
    /**
     * Estima la dificultad de la ruta basada en la distancia y el perfil de elevación
     */
    private fun estimateDifficulty(distanceKm: Double, points: List<LatLng>): String {
        // Simplificación: basamos la dificultad solo en la distancia
        return when {
            distanceKm < 5.0 -> "Fácil"
            distanceKm < 10.0 -> "Media"
            else -> "Difícil"
        }
    }
    
    /**
     * Extrae todos los puntos del archivo GPX proporcionado
     */
    fun extractPointsFromGpxString(gpxContent: String): List<LatLng> {
        val points = mutableListOf<LatLng>()
        
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(gpxContent.reader())
            
            var eventType = parser.eventType
            var inTrackPoint = false
            var lat = 0.0
            var lon = 0.0
            
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (tagName == "trkpt") {
                            inTrackPoint = true
                            lat = parser.getAttributeValue(null, "lat").toDouble()
                            lon = parser.getAttributeValue(null, "lon").toDouble()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (tagName == "trkpt" && inTrackPoint) {
                            points.add(LatLng(lat, lon))
                            inTrackPoint = false
                        }
                    }
                }
                eventType = parser.next()
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Error extracting points from GPX string", e)
        }
        
        return points
    }
} 