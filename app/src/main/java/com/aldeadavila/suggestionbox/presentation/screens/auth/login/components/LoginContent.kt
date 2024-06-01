package com.aldeadavila.suggestionbox.presentation.screens.auth.login.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.presentation.components.AuthenticationField
import com.aldeadavila.suggestionbox.presentation.components.NormalTextComponent
import com.aldeadavila.suggestionbox.presentation.components.ProgressBar
import com.aldeadavila.suggestionbox.presentation.components.TitleTextComponent
import com.aldeadavila.suggestionbox.presentation.navigation.screen.auth.AuthScreen
import com.aldeadavila.suggestionbox.presentation.screens.auth.login.LoginViewModel
import com.aldeadavila.suggestionbox.presentation.util.Constants.FORGOT_PASSWORD
import com.aldeadavila.suggestionbox.presentation.util.Constants.NO_ACCOUNT
import com.aldeadavila.suggestionbox.presentation.util.Constants.SIGN_IN_BUTTON
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun LoginContent(
    navController: NavHostController,
    paddingValues: PaddingValues,
    vm: LoginViewModel = hiltViewModel()
) {

    val loginFlow = vm.loginFlow.collectAsState()
    val state = vm.state
    val context = LocalContext.current
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.password_visible)
    } else {
        painterResource(id = R.drawable.password_toggle)
    }
    val keyboard = LocalSoftwareKeyboardController.current

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .clickable { keyboard?.hide() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleTextComponent(value = stringResource(id = R.string.do_login))
        NormalTextComponent(value = stringResource(id = R.string.welcome))
        Spacer(modifier = Modifier.height(20.dp))

        AuthenticationField(text = state.email,
            placeHolder = "Email",
            isPasswordTextField = false,
            onValueChange = { vm.onEmailInput(it) },
            errorMsg = "*Enter valid email address",
            trailingIcon = {
                if (state.email.isNotBlank()) {
                    IconButton(onClick = { vm.onEmailInput("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Text"
                        )

                    }
                }
            })
        Spacer(modifier = Modifier.height(16.dp))
        AuthenticationField(
            text = state.password,
            placeHolder = "Password",
            isPasswordTextField = !passwordVisibility,
            onValueChange = { vm.onPasswordInput(it) },
            errorMsg = "*Enter valid password",
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon",
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Button(
            onClick = {
                keyboard?.hide()
                vm.login()
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
                    text = SIGN_IN_BUTTON,
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

        Column(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = NO_ACCOUNT,
                color = Color.Black,
                modifier = Modifier
                    .clickable {
                        navController.navigate(route = AuthScreen.Register.route)
                    }
                    .padding(top = 10.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = poppins
                ),
            )

            Image(
                painter = painterResource(id = R.drawable.logo_avaldeadavilacorporario),
                contentDescription = "Agrupación vecinal de Aldeadávila",
                modifier = Modifier.size(500.dp)
            )

        }

    }

    loginFlow.value.let { state ->
        when(state) {
            Response.Loading -> {
                ProgressBar()
            }
            is Response.Success -> {
                Toast.makeText(LocalContext.current, "Usuario logeado", Toast.LENGTH_LONG).show()
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, state.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }

            else -> {}
        }

    }

}