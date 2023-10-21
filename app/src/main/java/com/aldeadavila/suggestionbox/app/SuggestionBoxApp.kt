package com.aldeadavila.suggestionbox.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aldeadavila.suggestionbox.navigation.Screen
import com.aldeadavila.suggestionbox.navigation.SuggestionBoxAppRouter
import com.aldeadavila.suggestionbox.screens.SignUpScreen
import com.aldeadavila.suggestionbox.screens.TermAndConditionsScreen

@Composable
fun SuggestionBoxApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = SuggestionBoxAppRouter.currentScreen, label = "") { currentState ->
            when(currentState.value) {
                is Screen.SignUpScreen -> { SignUpScreen() }
                is Screen.TermsAndConditionsScreen -> { TermAndConditionsScreen() }
            }
        }
    }
}

