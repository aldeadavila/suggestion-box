package com.aldeadavila.suggestionbox.domain.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Suggestion(
    @SerializedName("id")val id: String? = null,
    @SerializedName("name")val name: String,
    @SerializedName("description")val description: String,
    @SerializedName("id_user")val idUser: String,
    @SerializedName("id_category")val idCategory: String,
    @SerializedName("image1")val image1: String? = null,
    @SerializedName("image2")val image2: String? = null,
    @SerializedName("images_to_update")val imagesToUpdate: List<Int>? = listOf()
): Serializable {

    fun toJson(): String = Gson().toJson(
        Suggestion(
            id,
            name,
            description,
            idUser,
            idCategory,
            if (!image1.isNullOrBlank()) URLEncoder.encode(
                image1,
                StandardCharsets.UTF_8.toString()
            ) else "",
            if (!image2.isNullOrBlank()) URLEncoder.encode(
                image2,
                StandardCharsets.UTF_8.toString()
            ) else "",
            imagesToUpdate
        )
    )

    companion object {
        fun fromJson(data: String): Suggestion = Gson().fromJson(data, Suggestion::class.java)
    }
}
