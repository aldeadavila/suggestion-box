package com.aldeadavila.suggestionbox.presentation.screens.profile.info.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.MainActivity
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.components.AlertDialogSuggestion
import com.aldeadavila.suggestionbox.presentation.navigation.AuthScreen
import com.aldeadavila.suggestionbox.presentation.navigation.DetailsScreen
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileViewModel
import com.aldeadavila.suggestionbox.presentation.util.Constants
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins
import kotlinx.coroutines.launch

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    vm: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    val coroutineScope = rememberCoroutineScope()
    val openAlertDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(bottom = 80.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_orange),
            contentDescription = "",
            contentScale = ContentScale.Crop,

            )
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Cerrar sesión"
                )
                IconButton(
                    onClick = {
                        vm.logout()
                        activity?.finish()
                        activity?.startActivity(Intent(activity, MainActivity::class.java))
                    }
                ) {
                    Image(
                        modifier = Modifier.size(35.dp),
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = ""
                    )
                }
            }

            if (!vm.userData.profileImagePathUrl.isNullOrBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    model = vm.userData.profileImagePathUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = ""
                )
            }

            // Spacer(modifier = Modifier.weight(1f))

            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        if (vm.isanonymous() == true) {
                            Text(
                                text = "Usuario Anónimo",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        } else {
                            Text(text = vm.userData.nickname)
                            Text(
                                text = "Usuario",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        if (vm.isanonymous() == true) {
                            Text(
                                text = "Sin correo electrónico",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        } else {
                            Text(text = vm.userData.email)
                            Text(
                                text = "Correo electrónico",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {

                    openAlertDialog.value = true

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                if (vm.isanonymous() == false) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(38.dp)
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
                            text = Constants.DELETE_USER,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = md_theme_light_tertiaryContainer,
                                fontFamily = poppins
                            ),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    navHostController.navigate(
                        route = DetailsScreen.ProfileUpdate.passUser(vm.userData.toJson())
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                if (vm.isanonymous() == false) {
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
                            text = Constants.UPDATE_INFO_BUTTON,
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

            if (openAlertDialog.value) {
                AlertDialogSuggestion(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        coroutineScope.launch {
                            vm.onDeleteMyAccountClick()
                            vm.logout()
                            activity?.finish()
                            activity?.startActivity(Intent(activity, MainActivity::class.java))
                        }
                    },
                    dialogTitle = "Borrar usuario",
                    dialogText = "¿Estás seguro de que quiere borrar su usuario?",
                    icon = Icons.Default.Info
                )
            }
        }
    }

}
