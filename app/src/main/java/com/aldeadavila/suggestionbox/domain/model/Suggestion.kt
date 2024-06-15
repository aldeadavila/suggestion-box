package com.aldeadavila.suggestionbox.domain.model


import com.google.firebase.Timestamp
import com.google.firebase.database.ServerValue
import com.google.firebase.firestore.PropertyName
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.type.Date
import java.io.Serializable
import java.time.Instant

data class Suggestion(
    @PropertyName("title") val title: String = "",
    @PropertyName("description") val description: String = "",
    @PropertyName("user_id") val user_id: String = "",
    @PropertyName("category") val category: String = "",
    @PropertyName("images") var images: MutableList<String> = mutableListOf(),
    @PropertyName("created_at") var created_at: Timestamp = Timestamp.now(),
    @PropertyName("user") var user: User = User(),


): Serializable {

    fun toJson(): String = Gson().toJson(
        Suggestion(
            title,
            description,
            user_id,
            category,
            images,
            created_at
        )
    )

    companion object {
        fun fromJson(data: String): Suggestion = Gson().fromJson(data, Suggestion::class.java)
    }
}
