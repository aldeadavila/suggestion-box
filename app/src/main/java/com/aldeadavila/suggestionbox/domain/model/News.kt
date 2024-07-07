package com.aldeadavila.suggestionbox.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName
import com.google.gson.Gson
import java.io.Serializable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class News(
    @PropertyName("news_id") var news_id: String = "",
    @PropertyName("title") var title: String = "",
    @PropertyName("link") var link: String = "",
    @PropertyName("created_at") var created_at: Timestamp = Timestamp.now(),
): Serializable {
    fun toJson(): String = Gson().toJson(
        News(
            news_id,
            title,
            URLEncoder.encode(link, StandardCharsets.UTF_8.toString()),
            created_at
        )
    )

    companion object {
        fun fromJson(data: String): News = Gson().fromJson(data, News::class.java)
    }
}
