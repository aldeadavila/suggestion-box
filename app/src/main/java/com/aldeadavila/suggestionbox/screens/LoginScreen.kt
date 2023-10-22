package com.aldeadavila.suggestionbox.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.components.ButtonComponent
import com.aldeadavila.suggestionbox.components.MyPasswordTextField
import com.aldeadavila.suggestionbox.components.MyTextField
import com.aldeadavila.suggestionbox.components.NormalTextComponent
import com.aldeadavila.suggestionbox.components.SmallTextComponent
import com.aldeadavila.suggestionbox.components.TitleTextComponent
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary

@Composable
fun LoginScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TitleTextComponent(value = stringResource(id = R.string.do_login))
            NormalTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(
                labelValue = stringResource(id = R.string.email),
                icon = Icons.Rounded.Email
            )
            MyPasswordTextField(
                labelValue = stringResource(id = R.string.password),
                icon = Icons.Rounded.Lock
            )
            SmallTextComponent(stringResource(id = R.string.forget_password),
                modifier = Modifier.align(Alignment.End).padding(top=10.dp),
                color = md_theme_light_primary
            )
            ButtonComponent(stringResource(id = R.string.sign_in))
            SmallTextComponent(stringResource(id = R.string.new_account),
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
            SmallTextComponent(stringResource(id = R.string.guest),
                modifier = Modifier.padding(top=30.dp),
                color = Color.Black
            )
        }
    }
}