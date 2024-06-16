package com.aldeadavila.suggestionbox.domain.model


import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName
import com.google.gson.Gson
import java.io.Serializable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Suggestion(
    @PropertyName("suggestion_id") var suggestion_id: String = "",
    @PropertyName("title") val title: String = "",
    @PropertyName("description") val description: String = "",
    @PropertyName("user_id") val user_id: String = "",
    @PropertyName("category") val category: String = "",
    @PropertyName("images") var images: MutableList<String> = mutableListOf(),
    @PropertyName("created_at") var created_at: Timestamp = Timestamp.now(),
    @PropertyName("user") var user: User? = null,
    @PropertyName("comments") var comments: MutableList<Comment>? = mutableListOf(),


    ): Serializable {

    fun toJson(): String = Gson().toJson(
        Suggestion(
            suggestion_id,
            title,
            description,
            user_id,
            category,
            toEncode(images),
            created_at,
            user,
            comments
        )
    )

    fun toEncode(images: MutableList<String>): MutableList<String> {
        if (images[0] != "") images[0] = URLEncoder.encode(images[0], StandardCharsets.UTF_8.toString())
        if (images[1] != "") images[1] = URLEncoder.encode(images[1], StandardCharsets.UTF_8.toString())
        return mutableListOf(images[0], images[1])
    }

    companion object {
        fun fromJson(data: String): Suggestion = Gson().fromJson(data, Suggestion::class.java)
    }
}
