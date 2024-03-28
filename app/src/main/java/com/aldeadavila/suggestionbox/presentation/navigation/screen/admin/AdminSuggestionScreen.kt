package com.aldeadavila.suggestionbox.presentation.navigation.screen.admin

import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCommentScreen
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientSuggestionScreen

sealed class AdminSuggestionScreen(val route: String) {

    object SuggestionDetail: AdminSuggestionScreen("admin/suggestions/detail/{suggestion}") {
        fun passSuggestion(suggestion: String) = "admin/suggestions/detail/$suggestion"
    }

    object SuggestionUpdate: AdminSuggestionScreen("admin/suggestions/update/{suggestion}") {
        fun passSuggestion(comment: String) = "admin/suggestions/update/$comment"
    }
}