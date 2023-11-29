package com.aldeadavila.suggestionbox.presentation.screens.profile.update.components

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.components.DefaultTextField
import com.aldeadavila.suggestionbox.presentation.components.DialagoCapturePicture
import com.aldeadavila.suggestionbox.presentation.screens.profile.update.ProfileUpdateViewModel
import com.aldeadavila.suggestionbox.presentation.util.Constants
import com.aldeadavila.suggestionbox.ui.theme.SuggestionBoxTheme
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins

@Composable
fun ProfileUpdateContent(
    paddingValues: PaddingValues, vm: ProfileUpdateViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    val state = vm.state
    val stateDialog = remember { mutableStateOf(false) }
    vm.resultingActivityHandler.handle()

    DialagoCapturePicture(state = stateDialog,
        takePhoto = { vm.takePhoto() },
        pickImage = { vm.pickImage() })

    Box(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_blue),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,

            )
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.padding(40.dp))
            Log.d(
                "ProfileUpdateContent",
                "Image: ${state.image}"
            )
            if (!state.image.isNullOrBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                        .clickable { stateDialog.value = true },
                    model = state.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                        .clickable { stateDialog.value = true },
                    painter = painterResource(id = R.drawable.user_image),
                    contentDescription = ""
                )
            }

            // Spacer(modifier = Modifier.weight(1f))

            Column(modifier = Modifier.padding(20.dp)) {


                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.name,
                    onValueChange = { vm.onNameInput(it) },
                    label = "Nombre",
                    icon = Icons.Default.Person,
                    contentDescription = ""
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.lastname,
                    onValueChange = { vm.onLastNameInput(it) },
                    label = "Apellidos",
                    icon = Icons.Default.Person,
                    contentDescription = ""
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.phone,
                    onValueChange = { vm.onPhoneInput(it) },
                    label = "Teléfono",
                    icon = Icons.Default.Phone,
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    vm.onUpdate()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
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
                        text = Constants.CONFIRM_BUTTON,
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

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProfileContentPreview() {
    SuggestionBoxTheme {
        ProfileUpdateContent(paddingValues = PaddingValues())
    }
}