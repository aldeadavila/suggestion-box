package com.aldeadavila.suggestionbox.ui.screens.profile.update.components

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.R.drawable.profile_background
import com.aldeadavila.suggestionbox.ui.components.DefaultButton
import com.aldeadavila.suggestionbox.ui.components.DefaultTextField
import com.aldeadavila.suggestionbox.ui.components.DialagoCapturePicture
import com.aldeadavila.suggestionbox.ui.screens.profile.update.ProfileUpdateViewModel
import com.aldeadavila.suggestionbox.ui.theme.SuggestionBoxTheme

@Composable
fun ProfileUpdateContent(
    paddingValues: PaddingValues,
    vm: ProfileUpdateViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    val state = vm.state
    val stateDialog = remember { mutableStateOf(false) }
    vm.resultingActivityHandler.handle()

    DialagoCapturePicture(state = stateDialog,
        takePhoto = { vm.takePhoto() },
        pickImage = { vm.pickImage() }
    )

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = profile_background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToScale(
                    0.6f,
                    0.6f,
                    0.6f,
                    1f
                )
            })
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

            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(
                    topEnd = 40.dp,
                    topStart = 40.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.7f),
                )
            ) {
                Column(modifier = Modifier.padding(20.dp))
                {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 20.dp),
                        text = "ACTUALIZAR",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )

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
            }
            Spacer(modifier = Modifier.height(40.dp))
            DefaultButton(modifier = Modifier.fillMaxWidth(),
                text = "Confirmar",
                onClick = {
                    vm.onUpdate()
                })

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