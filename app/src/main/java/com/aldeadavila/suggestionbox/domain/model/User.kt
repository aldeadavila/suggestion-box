package com.aldeadavila.suggestionbox.domain.model

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Date

data class User(
    @PropertyName("id") var id: String = "",
    @PropertyName("nickname") var nickname: String =  "",
    @PropertyName("email") var email: String = "",
    @PropertyName("password") var password: String = "",
    @PropertyName("profile_image_path_url") var profileImagePathUrl: String? = "",
    @PropertyName("profile_image_path") var profileImagePath: String? = "",
    @PropertyName("roles") var roles: List<String>? = emptyList(),
    @PropertyName("preferences") var preferences: List<String>? = emptyList(),
    @PropertyName("is_premium") var isPremium: Boolean = false,
    @PropertyName("date_created") @ServerTimestamp var dateCreated: Date? = null,
) {
    fun toJson(): String = Gson().toJson(User(
        id,
        nickname,
        email,
        password,
        profileImagePathUrl,
        profileImagePath,
        roles,
        preferences,
        isPremium,
        dateCreated
    ))

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}
