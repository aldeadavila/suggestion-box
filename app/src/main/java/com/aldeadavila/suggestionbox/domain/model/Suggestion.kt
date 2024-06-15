package com.aldeadavila.suggestionbox.domain.model


import com.google.firebase.Timestamp
import com.google.firebase.database.ServerValue
import com.google.firebase.firestore.PropertyName
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import java.io.Serializable

data class Suggestion(
    @PropertyName("id") var id: String = "",
    @PropertyName("title") val title: String = "",
    @PropertyName("description") val description: String = "",
    @PropertyName("user_id") val user_id: String = "",
    @PropertyName("category") val category: String = "",
    @PropertyName("images") var images: MutableList<String> = mutableListOf(),
    @PropertyName("created_at") var created_at: Timestamp

): Serializable {

    fun toJson(): String = Gson().toJson(
        Suggestion(
            id,
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
