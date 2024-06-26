package com.aldeadavila.suggestionbox.presentation.screens.profile.update.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.aldeadavila.suggestionbox.presentation.util.ComposeFileProvider
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
    val state = vm.state
    val stateDialog = remember { mutableStateOf(false) }
    vm.resultingActivityHandler.handle()
    val keyboard = LocalSoftwareKeyboardController.current

    DialagoCapturePicture(state = stateDialog,
        takePhoto = { vm.takePhoto() },
        pickImage = { vm.pickImage() })

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .clickable{keyboard?.hide()},
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
                "Image: ${state.profileImagePathUrl}"
            )
            if (vm.state.profileImagePathUrl != "") {
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                        .clickable { stateDialog.value = true },
                    model = vm.state.profileImagePathUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            stateDialog.value = true
                        },
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = ""
                )
            }

            // Spacer(modifier = Modifier.weight(1f))

            Column(modifier = Modifier.padding(20.dp)) {

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.nickname,
                    onValueChange = { vm.onNicknameInput(it) },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    contentDescription = ""
                )

            }

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    vm.saveImage()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
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