package com.aldeadavila.suggestionbox.presentation.navigation.screen.client

sealed class ClientCategoryScreen(val route: String) {

    object SuggestionList: ClientCategoryScreen("client/category/suggestions/list/{category}") {
        fun passCategory(category: String) = "client/category/suggestions/list/$category"
    }

    object SuggestionCreate: ClientCategoryScreen("client/category/suggestions/create/{category}") {
        fun passCategory(category: String) = "client/category/suggestions/create/$category"
    }

    object SuggestionUpdate: ClientCategoryScreen("client/category/suggestions/update/{suggestion}") {
        fun passSuggestion(suggestion: String) = "client/category/suggestions/update/$suggestion"
    }

}
