package com.aldeadavila.suggestionbox.presentation.navigation.screen.client

sealed class ClientCategoryScreen(val route: String) {

    object SuggestionList: ClientCategoryScreen("client/category/suggestions/list/{category}") {
        fun passCategory(category: String) = "client/category/suggestions/list/$category"
    }

}
