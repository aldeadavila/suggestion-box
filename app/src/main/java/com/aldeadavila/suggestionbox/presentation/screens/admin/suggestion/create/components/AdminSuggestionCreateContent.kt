package com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.components.DefaultButton
import com.aldeadavila.suggestionbox.presentation.components.DefaultTextField
import com.aldeadavila.suggestionbox.presentation.components.DialagoCapturePicture
import com.aldeadavila.suggestionbox.presentation.screens.admin.suggestion.create.AdminProductCreateViewModel

@Composable
fun AdminSuggestionCreateContent(
    paddingValues: PaddingValues,
    vm: AdminProductCreateViewModel = hiltViewModel()
) {

    val state = vm.state
    vm.resultingActivityHandler.handle()
    val stateDialog = remember { mutableStateOf(false) }
    val stateDialogImageNumber = remember { mutableStateOf(1) }

    DialagoCapturePicture(
        state = stateDialog,
        takePhoto = { vm.takePhoto(stateDialogImageNumber.value) },
        pickImage = { vm.pickImage(stateDialogImageNumber.value) }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            if (!state.image1.isNullOrBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape)
                        .clickable {
                            stateDialog.value = true
                            stateDialogImageNumber.value = 1
                        },
                    model = state.image1,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            stateDialog.value = true
                            stateDialogImageNumber.value = 1
                        },
                    painter = painterResource(id = R.drawable.image_add),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            if (!state.image2.isNullOrBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            stateDialog.value = true
                            stateDialogImageNumber.value = 2
                        },
                    model = state.image2,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape)
                        .clickable {
                            stateDialog.value = true
                            stateDialogImageNumber.value = 2
                        },
                    painter = painterResource(id = R.drawable.image_add),
                    contentDescription = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(
                topEnd = 40.dp,
                topStart = 40.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(modifier = Modifier.padding(20.dp))
            {
                Text(
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    text = "SUGERENCIA",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Text(
                    text = vm.category.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.name,
                    onValueChange = { vm.onNameInput(it) },
                    label = "Título de la sugerencia",
                    icon = Icons.Default.List,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.description,
                    onValueChange = { vm.onDescriptionInput(it) },
                    label = "Descripción",
                    icon = Icons.Default.Info,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.idUser,
                    onValueChange = { vm.onIdUserInput(it) },
                    label = "Precio",
                    icon = Icons.Default.Info,
                    contentDescription = "",
                    keyboardType = KeyboardType.Number
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            DefaultButton(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
                text = "Crear Sugerencia",
                onClick = {
                    vm.createProduct()
                }
            )
        }
    }

}