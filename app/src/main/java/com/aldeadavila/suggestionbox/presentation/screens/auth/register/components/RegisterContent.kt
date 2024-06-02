package com.aldeadavila.suggestionbox.presentation.screens.auth.register.components

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.DefaultTextField
import com.aldeadavila.suggestionbox.presentation.components.NormalTextComponent
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.components.TitleTextComponent
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.aldeadavila.suggestionbox.presentation.navigation.screen.auth.AuthScreen
import com.aldeadavila.suggestionbox.presentation.screens.auth.register.RegisterViewModel
import com.aldeadavila.suggestionbox.presentation.util.Constants
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    vm: RegisterViewModel = hiltViewModel()
) {

    val state = vm.state
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val signUpFlow = vm.signUpFlow.collectAsState()

    LaunchedEffect(
        key1 = vm.errorMessage
    ) {
        if (vm.errorMessage != "") {
            Toast.makeText(
                context,
                vm.errorMessage,
                Toast.LENGTH_LONG
            ).show()
            vm.errorMessage = ""
        }

    }
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
                .padding(start = 50.dp)
                .size(400.dp),
            painter = painterResource(id = R.drawable.bg_orange),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopEnd

        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            TitleTextComponent(value = stringResource(id = R.string.sign_up))
            NormalTextComponent(value = stringResource(id = R.string.add_comments))
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 20.dp,
                        end = 30.dp,
                        bottom = 30.dp
                    )
                    .verticalScroll(rememberScrollState())
            ) {

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.nickname,
                    onValueChange = { vm.onNicknameInput(it) },
                    label = "Nombre de usuario",
                    icon = Icons.Outlined.Person,
                    contentDescription = ""
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = { vm.onEmailInput(it) },
                    label = "Email",
                    icon = Icons.Default.Email,
                    contentDescription = "",
                    keyboardType = KeyboardType.Email
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = { vm.onPasswordInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    contentDescription = "",
                    keyboardType = KeyboardType.Password,
                    hideText = true
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.confirmPassword,
                    onValueChange = { vm.onConfirmPasswordInput(it) },
                    label = "Confirmar contraseña",
                    icon = Icons.Outlined.Lock,
                    contentDescription = "",
                    keyboardType = KeyboardType.Password,
                    hideText = true
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        keyboard?.hide()
                        vm.onSignUp()
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
                            text = Constants.SIGN_UP_BUTTON,
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

    signUpFlow.value.let {
        when(it) {
            Response.Loading -> {
                ProgressBar()
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception.message ?: "Error desconocido", Toast.LENGTH_LONG)
            }
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    vm.createUser()
                    navHostController.popBackStack(AuthScreen.Login.route, inclusive = true)
                    navHostController.navigate(route = Graph.CLIENT)
                }
            }
            else -> {}
        }
    }
}