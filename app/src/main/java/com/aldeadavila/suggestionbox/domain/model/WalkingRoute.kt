package com.aldeadavila.suggestionbox.domain.model

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Modelo que representa una ruta de senderismo
 */
data class WalkingRoute(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val points: List<LatLng> = emptyList(),
    val distance: Double = 0.0, // en kilómetros
    val duration: Int = 0, // en minutos
    val difficulty: String = "", // fácil, media, difícil
    val isVisible: Boolean = true,
    val color: Int = 0xFF0288D1.toInt(), // Color azul por defecto
    val width: Float = 8f,
    val elevationGain: Double = 0.0 // desnivel positivo acumulado en metros
) : Serializable {
    
    fun toJson(): String = Gson().toJson(
        mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "points" to points.map { mapOf("lat" to it.latitude, "lng" to it.longitude) },
            "distance" to distance,
            "duration" to duration,
            "difficulty" to difficulty,
            "isVisible" to isVisible,
            "color" to color,
            "width" to width,
            "elevationGain" to elevationGain
        )
    )
    
    companion object {
        fun fromJson(data: String): WalkingRoute {
            val map = Gson().fromJson(data, Map::class.java) as Map<String, Any>
            val pointsList = (map["points"] as List<Map<String, Double>>).map { 
                LatLng(it["lat"] as Double, it["lng"] as Double) 
            }
            
            return WalkingRoute(
                id = map["id"] as String,
                name = map["name"] as String,
                description = map["description"] as String,
                points = pointsList,
                distance = map["distance"] as Double,
                duration = (map["duration"] as Double).toInt(),
                difficulty = map["difficulty"] as String,
                isVisible = map["isVisible"] as Boolean,
                color = (map["color"] as Double).toInt(),
                width = (map["width"] as Double).toFloat(),
                elevationGain = map["elevationGain"] as? Double ?: 0.0
            )
        }
        
        /**
         * Calcula la distancia aproximada de la ruta en kilómetros
         */
        fun calculateDistance(points: List<LatLng>): Double {
            if (points.size < 2) return 0.0
            
            var totalDistance = 0.0
            for (i in 0 until points.size - 1) {
                totalDistance += calculateHaversineDistance(points[i], points[i + 1])
            }
            
            return totalDistance
        }
        
        /**
         * Calcula la distancia entre dos puntos usando la fórmula del Haversine
         * Devuelve la distancia en kilómetros
         */
        private fun calculateHaversineDistance(point1: LatLng, point2: LatLng): Double {
            val R = 6371.0 // Radio de la Tierra en km
            
            val dLat = Math.toRadians(point2.latitude - point1.latitude)
            val dLon = Math.toRadians(point2.longitude - point1.longitude)
            
            val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(point1.latitude)) * Math.cos(Math.toRadians(point2.latitude)) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2)
            
            val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
            
            return R * c
        }
    }
} 