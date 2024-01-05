package com.aldeadavila.suggestionbox.domain.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment(
    @SerializedName("id")val id: String? = null,
    @SerializedName("content")val content: String,
    @SerializedName("id_user")val idUser: String,
    @SerializedName("id_suggestion")val idSuggestion: String,
): Serializable {

    fun toJson(): String = Gson().toJson(
        Comment(
            id,
            content,
            idUser,
            idSuggestion,
        )
    )

    companion object {
        fun fromJson(data: String): Comment = Gson().fromJson(data, Comment::class.java)
    }
}
