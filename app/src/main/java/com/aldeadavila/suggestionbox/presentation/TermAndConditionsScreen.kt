package com.aldeadavila.suggestionbox.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.components.TitleTextComponent

@Composable
fun TermAndConditionsScreen() {
    Surface (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)
    ) {
        TitleTextComponent(value = stringResource(id = R.string.use_terms))
    }
}

