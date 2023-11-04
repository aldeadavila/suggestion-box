package com.aldeadavila.suggestionbox.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class Suggestion(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val description: String  = "",
    val category: String= "",
    val image: String = "",
    val thumb: String? = "",
    @ServerTimestamp var lastModified: Timestamp? = null,
    )
{
    constructor() :  this("","", "", "", "", "", null)
}