package com.aldeadavila.suggestionbox.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialagoCapturePicture(
    state: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit
) {
    if (state.value) { //MOSTRAR EL DIALOG

        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.White),

            onDismissRequest = { state.value = false },
            title = {
                Text(
                    text = "Selecciona una opción",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            },
            confirmButton = {
                Button(
                    modifier = Modifier.width(130.dp),
                    onClick = {
                        state.value = false
                        pickImage()
                    },
                    content = {
                        Text(text = "Galería")
                    }
                )
            },
            dismissButton = {
                Button(
                    modifier = Modifier.width(130.dp),
                    onClick = {
                        state.value = false
                        takePhoto()
                    },
                    content = {
                        Text(text = "Cámara")
                    }
                )
            }
        )
    }
}