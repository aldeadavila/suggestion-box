package com.aldeadavila.suggestionbox.presentation.navigation.screen.client

sealed class ClientSuggestionScreen(val route: String) {

    object SuggestionDetail: ClientSuggestionScreen("client/suggestions/detail/{suggestion}") {
        fun passSuggestion(suggestion: String) = "client/suggestions/detail/$suggestion"
    }

}
