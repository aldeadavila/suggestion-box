package com.aldeadavila.suggestionbox.domain.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    @SerializedName("id") val id: String? = null,
    @SerializedName("nickname") var nickname: String,
    @SerializedName("email") val email: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("notification_token") val notificationToken: String? = null,
    @SerializedName("roles") val roles: List<Rol>? = null,
) {
    fun toJson(): String = Gson().toJson(User(
        id,
        nickname,
        email,
        password,
        if(!image.isNullOrBlank()) URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else  "",
        notificationToken,
        roles?.map { rol ->
            Rol.fromJson(rol.toJson())
        }
    ))

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}
