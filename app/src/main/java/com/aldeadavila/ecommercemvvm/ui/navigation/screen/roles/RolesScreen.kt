package com.aldeadavila.suggestionbox.ui.navigation.screen.roles

sealed class RolesScreen(val route: String) {
    object Roles: RolesScreen("roles")

}
