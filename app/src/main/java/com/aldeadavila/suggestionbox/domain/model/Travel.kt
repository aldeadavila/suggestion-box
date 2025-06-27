package com.aldeadavila.suggestionbox.domain.model

import com.google.gson.Gson
import java.util.Date

data class Travel(
    var id: String = "",
    var userId: String = "",
    var userName: String = "",
    var type: String = "",
    var direction: String = "",
    var date: Date? = null,
    var seats: Int = 0,
    var description: String = "",
    var paradas: List<String> = emptyList(),
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): Travel = Gson().fromJson(data, Travel::class.java)
        
        // Constantes para los tipos
        const val OFFER = "OFFER"
        const val REQUEST = "REQUEST"
        
        // Constantes para las direcciones
        const val TO_ALDEADAVILA = "TO_ALDEADAVILA"
        const val FROM_ALDEADAVILA = "FROM_ALDEADAVILA"
    }
} 