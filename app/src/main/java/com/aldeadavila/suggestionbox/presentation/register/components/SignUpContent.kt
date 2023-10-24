package com.aldeadavila.suggestionbox.presentation.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.components.CheckBoxComponent
import com.aldeadavila.suggestionbox.components.EmailField
import com.aldeadavila.suggestionbox.components.MyPasswordTextField
import com.aldeadavila.suggestionbox.components.MyTextField
import com.aldeadavila.suggestionbox.components.NormalTextComponent
import com.aldeadavila.suggestionbox.components.PasswordField
import com.aldeadavila.suggestionbox.components.SmallSpacer
import com.aldeadavila.suggestionbox.components.TitleTextComponent
import com.aldeadavila.suggestionbox.util.Constants.EMPTY_STRING


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpContent(
    padding: PaddingValues,
    signUp: (email: String, password: String) -> Unit,
    navigateBack: () -> Unit
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

    Column (
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TitleTextComponent(value = stringResource(id = R.string.sign_up))

        NormalTextComponent(value = stringResource(id = R.string.add_comments))
        Spacer(modifier = Modifier.height(20.dp))
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        SmallSpacer()
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            }
        )
        SmallSpacer()

        MyPasswordTextField(
            labelValue = stringResource(id = R.string.password_again),
            icon = Icons.Rounded.Lock
        )
        Spacer(modifier = Modifier.height(20.dp))
        CheckBoxComponent(value = stringResource(id = R.string.privacy_policy),
            onTextSelected = {

            })

        //ButtonComponent(stringResource(id = R.string.create_account))

        Image(
            painter = painterResource(id = R.drawable.escudo_aldeadavila),

            contentDescription = "Escudo de Aldeadávila",
            modifier = Modifier
                .padding(top=10.dp)
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        NormalTextComponent(value = stringResource(id = R.string.guest))
    }

}
