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
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
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
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.screens.profile.info.ProfileViewModel
import com.aldeadavila.suggestionbox.presentation.util.Constants
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    navvHostController: NavHostController,
    vm: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity

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
            IconButton(modifier = Modifier
                .align(Alignment.End)
                .padding(
                    end = 15.dp,
                    top = 15.dp
                ),
                onClick = {
                    vm.logout()
                    activity?.finish()
                    activity?.startActivity(
                        Intent(
                            activity,
                            MainActivity::class.java
                        )
                    )
                }) {
                Image(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = ""
                )

            }
            IconButton(modifier = Modifier
                .align(Alignment.End)
                .padding(
                    end = 15.dp,
                    top = 15.dp
                ),
                onClick = {
                    activity?.finish()
                    activity?.startActivity(
                        Intent(
                            activity,
                            MainActivity::class.java
                        )
                    )
                }) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "",
                    tint = Color.White
                )

            }
            if (!vm.user?.image.isNullOrBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    model = vm.user?.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.user_image),
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
                        Text(text = "${vm.user?.name} ${vm.user?.lastname}")
                        Text(
                            text = "Nombre de usuario",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
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
                        Text(text = vm.user?.email ?: "")
                        Text(
                            text = "Correo electrónico",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        Text(text = vm.user?.phone ?: "")
                        Text(
                            text = "Teléfono",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navvHostController.navigate(route = "${Graph.PROFILE}/${vm.user?.toJson()}")
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
    }

}
