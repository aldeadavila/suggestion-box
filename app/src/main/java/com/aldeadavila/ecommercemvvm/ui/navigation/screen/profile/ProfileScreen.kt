package com.aldeadavila.suggestionbox.ui.navigation.screen.profile

sealed class ProfileScreen(val route: String) {
    object ProfileUpdate: ProfileScreen("profile/update")

}
