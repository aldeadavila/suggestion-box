package com.aldeadavila.suggestionbox.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.aldeadavila.suggestionbox.ui.theme.poppins

@Composable
fun NormalTextComponent(value: String) {
    Text(
        //  modifier = Modifier.padding(horizontal = 50.dp),
        text = value,
        color = Color.Black,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontFamily = poppins
        ),
    )
}