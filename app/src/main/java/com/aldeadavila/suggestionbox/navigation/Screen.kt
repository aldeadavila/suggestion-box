package com.aldeadavila.suggestionbox.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen() {
    object SignUpScreen : Screen()
    object TermsAndConditionsScreen: Screen()
}

object SuggestionBoxAppRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination:Screen) {
        currentScreen.value = destination
    }
}
