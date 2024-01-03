package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.components.DefaultTextField
import com.aldeadavila.suggestionbox.presentation.components.DialagoCapturePicture
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.create.ClientProductCreateViewModel
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins


@Composable
fun ClientSuggestionCreateContent(
    paddingValues: PaddingValues,
    vm: ClientProductCreateViewModel = hiltViewModel()
) {

    val state = vm.state
    val category = vm.category.name.dropLast(1)
    vm.resultingActivityHandler.handle()
    val stateDialog = remember { mutableStateOf(false) }
    val stateDialogImageNumber = remember { mutableStateOf(1) }

    DialagoCapturePicture(
        state = stateDialog,
        takePhoto = { vm.takePhoto(stateDialogImageNumber.value) },
        pickImage = { vm.pickImage(stateDialogImageNumber.value) }
    )

    Box(
        modifier = Modifier
            .padding(
                paddingValues = paddingValues
            )
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .padding(start = 50.dp)
                .size(400.dp),
            painter = painterResource(id = R.drawable.bg_green),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopEnd
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues).padding(20.dp),
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
                        .clip(RoundedCornerShape(20.dp))
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
                            .clip(RoundedCornerShape(20.dp))
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

            Text(
                text =  if (category.last() == 'o') "Nuevo $category" else "Nueva $category",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.name,
                onValueChange = { vm.onNameInput(it) },
                label = if (category.last() == 'o') "Título del $category" else "Título de la $category",
                icon = Icons.Default.List,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.description,
                onValueChange = { vm.onDescriptionInput(it) },
                label = if (category.last() == 'o') "Descripción del $category" else "Descripción de la $category",
                icon = Icons.Default.Info,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    vm.createSuggestion()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(48.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    md_theme_light_primary,
                                    md_theme_light_secondary
                                )
                            ),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Crear $category",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            color = md_theme_light_tertiaryContainer,
                            fontFamily = poppins
                        ),
                    )
                }
            }

        }
    }
}
