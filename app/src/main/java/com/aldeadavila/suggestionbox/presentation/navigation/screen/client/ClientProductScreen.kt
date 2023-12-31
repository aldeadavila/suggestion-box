package com.aldeadavila.suggestionbox.presentation.navigation.screen.client

sealed class ClientProductScreen(val route: String) {

    object ProductDetail: ClientProductScreen("client/suggestions/detail/{suggestion}") {
        fun passProduct(suggestion: String) = "client/suggestions/detail/$suggestion"
    }

}
