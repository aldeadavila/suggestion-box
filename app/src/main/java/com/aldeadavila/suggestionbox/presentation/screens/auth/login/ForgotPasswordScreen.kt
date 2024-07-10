package com.aldeadavila.suggestionbox.presentation.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.usecase.auth.AuthUseCases
import com.aldeadavila.suggestionbox.presentation.components.TitleTextComponent
import com.aldeadavila.suggestionbox.presentation.navigation.AuthScreen
import com.aldeadavila.suggestionbox.presentation.util.Constants
import com.aldeadavila.suggestionbox.ui.theme.Purple40
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins

import kotlinx.coroutines.launch
@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController,
    vm: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleTextComponent(value = stringResource(id = R.string.do_reset_password))
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            label = { Text(text = "Correo electrónico") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it })

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                scope.launch {
                    when(val res = vm.resetPassword(email)) {
                        is Response.Success -> {
                            //  analytics.logButtonClicked(buttonName = "Reset password $email")
                            Toast.makeText(context, "Correo enviado", Toast.LENGTH_SHORT).show()
                            navHostController.navigate(route = AuthScreen.Login.route)
                        }
                        is Response.Failure -> {
                            //    analytics.logError(error = "Reset password error $email : ${res.errorMessage}")
                            Toast.makeText(context, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                        }
                    }
                }
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
                    .padding(40.dp, 0.dp, 40.dp, 0.dp)
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
                    text = "Recuperar contraseña",
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Revisa tu bandeja de spam",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = md_theme_light_secondary,
                fontFamily = poppins
            ),
        )
    }
}