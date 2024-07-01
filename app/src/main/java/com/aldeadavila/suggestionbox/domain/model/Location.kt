package com.aldeadavila.suggestionbox.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName
import com.google.gson.Gson
import java.io.Serializable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Location(
    @PropertyName("location_id") var location_id: String = "",
    @PropertyName("name") var name: String = "",
    @PropertyName("phone") var phone: String = "",
    @PropertyName("address") var address: String = "",
    @PropertyName("description") var description: String = "",
    @PropertyName("email") var email: String = "",
    @PropertyName("coordinates") var coordinates: GeoPoint = GeoPoint(0.0, 0.0),
    @PropertyName("images_url") var images_url: MutableList<String> = mutableListOf(),
    @PropertyName("link") var link: String = "",
    @PropertyName("type") var type: String = "",
): Serializable {

    fun toJson(): String = Gson().toJson(
        Location(
            location_id,
            name,
            phone,
            address,
            description,
            email,
            coordinates,
            toEncode(images_url),
            link = URLEncoder.encode(link, StandardCharsets.UTF_8.toString()),
            type
        )
    )

    fun toEncode(images: MutableList<String>): MutableList<String> {
        if (images[0] != "") images[0] = URLEncoder.encode(images[0], StandardCharsets.UTF_8.toString())
        if (images[1] != "") images[1] = URLEncoder.encode(images[1], StandardCharsets.UTF_8.toString())
        return mutableListOf(images[0], images[1])
    }



    companion object {
        fun fromJson(data: String): Suggestion = Gson().fromJson(data, Suggestion::class.java)

        fun toLatLng(coordinates: GeoPoint): LatLng {
            return LatLng(coordinates.latitude, coordinates.longitude)
        }
    }
}
