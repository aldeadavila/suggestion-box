package com.aldeadavila.suggestionbox.domain.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment(
    @SerializedName("comment_id") var comment_id: String = "",
    @SerializedName("content")val content: String = "",
    @SerializedName("id_user")val user_id: String = "",
    @SerializedName("suggestion_id")val suggestion_id: String = "",
): Serializable {

    fun toJson(): String = Gson().toJson(
        Comment(
            comment_id,
            content,
            user_id,
            suggestion_id
        )
    )

    companion object {
        fun fromJson(data: String): Comment = Gson().fromJson(data, Comment::class.java)
    }
}
