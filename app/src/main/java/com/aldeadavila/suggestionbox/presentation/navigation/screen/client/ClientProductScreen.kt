package com.aldeadavila.suggestionbox.presentation.navigation.screen.client

sealed class ClientProductScreen(val route: String) {

    object ProductDetail: ClientProductScreen("client/suggestions/detail/{product}") {
        fun passProduct(product: String) = "client/suggestions/detail/$product"
    }

}
