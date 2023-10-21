package com.aldeadavila.suggestionbox

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SuggestionBoxApp : Application() {

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}