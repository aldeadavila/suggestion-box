package com.aldeadavila.suggestionbox.presentation.login.component

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.components.EmailField
import com.aldeadavila.suggestionbox.components.NormalTextComponent
import com.aldeadavila.suggestionbox.components.PasswordField
import com.aldeadavila.suggestionbox.components.SmallTextComponent
import com.aldeadavila.suggestionbox.components.TitleTextComponent
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins
import com.aldeadavila.suggestionbox.util.Constants.EMPTY_STRING
import com.aldeadavila.suggestionbox.util.Constants.FORGOT_PASSWORD
import com.aldeadavila.suggestionbox.util.Constants.SIGN_IN_BUTTON

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginContent(
    padding: PaddingValues,
    signIn: (email: String, password: String) -> Unit,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit
) {

    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )

    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleTextComponent(value = stringResource(id = R.string.do_login))
        NormalTextComponent(value = stringResource(id = R.string.welcome))
        Spacer(modifier = Modifier.height(20.dp))
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            }
        )
        Text(
            text = FORGOT_PASSWORD,
            color = md_theme_light_primary,
            modifier = Modifier.clickable {
                navigateToForgotPasswordScreen()
            }.padding(top = 10.dp),
            style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontFamily = poppins
            ),
        )

        Button(
            onClick = {
                keyboard?.hide()
                signIn(email.text, password.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding( top = 20.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp).background(
                    brush = Brush.horizontalGradient(listOf(md_theme_light_primary, md_theme_light_secondary)),
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

        SmallTextComponent(
            stringResource(id = R.string.new_account),
            modifier = Modifier.padding(top=30.dp),
            color = Color.Black
        )
        Image(
            painter = painterResource(id = R.drawable.escudo_aldeadavila),

            contentDescription = "Escudo de Aldeadávila",
            modifier = Modifier
                .padding(top=10.dp)
                .size(150.dp)
        )
        SmallTextComponent(
            stringResource(id = R.string.guest),
            modifier = Modifier.padding(top=30.dp),
            color = Color.Black
        )
    }
    
}