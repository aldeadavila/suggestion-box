package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.components

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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.update.ClientSuggestionUpdateViewModel
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins


@Composable
fun ClientSuggestionUpdateContent(
    paddingValues: PaddingValues,
    vm: ClientSuggestionUpdateViewModel = hiltViewModel()
) {

    val state = vm.state
    vm.resultingActivityHandler.handle()
    val stateDialog = remember { mutableStateOf(false) }
    val stateDialogImageNumber = remember { mutableStateOf(1) }
    val keyboard = LocalSoftwareKeyboardController.current

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
            .clickable { keyboard?.hide() },
    ) {
        Image(
            modifier = Modifier
                .padding(start = 25.dp)
                .size(400.dp),
            painter = painterResource(id = R.drawable.bg_blue),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopEnd
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
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
                        painter = painterResource(id = R.drawable.ic_add),
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
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = ""
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(modifier = Modifier.padding(20.dp))
            {
                Text(
                    text = vm.suggestion.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.title,
                    onValueChange = { vm.onNameInput(it) },
                    label = "Nombre de la sugerencia",
                    icon = Icons.AutoMirrored.Filled.List,
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

                vm.radioOptions.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .selectable(
                                selected = (option.category.lowercase() == state.category.lowercase()),
                                onClick = { vm.onCategoryInput(option.category) }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (option.category.lowercase()  == state.category.lowercase() ),
                            onClick = { vm.onCategoryInput(option.category) }
                        )
                        Row {
                            Text(
                                modifier = Modifier
                                    .width(150.dp)
                                    .padding(12.dp),
                                text = option.category
                            )
                            Image(
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(8.dp),
                                painter = painterResource(id = option.image),
                                contentDescription = ""
                            )
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                   vm.onUpdateSuggestion()
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
                        text = "Actualice su sugerencia",
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