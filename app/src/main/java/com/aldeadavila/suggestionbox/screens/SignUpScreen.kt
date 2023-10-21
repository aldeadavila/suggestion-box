package com.aldeadavila.suggestionbox.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.components.CheckBoxComponent
import com.aldeadavila.suggestionbox.components.MyPasswordTextField
import com.aldeadavila.suggestionbox.components.MyTextField
import com.aldeadavila.suggestionbox.components.NormalTextComponent
import com.aldeadavila.suggestionbox.components.TitleTextComponent
import com.aldeadavila.suggestionbox.navigation.Screen
import com.aldeadavila.suggestionbox.navigation.SuggestionBoxAppRouter

@Composable
fun SignUpScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()

            .background(Color.White)

    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TitleTextComponent(value = stringResource(id = R.string.sign_up))

            NormalTextComponent(value = stringResource(id = R.string.add_comments))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(
                labelValue = stringResource(id = R.string.first_name),
                icon = Icons.Rounded.Person
            )
            MyTextField(
                labelValue = stringResource(id = R.string.email),
                icon = Icons.Rounded.Email
            )
            MyPasswordTextField(
                labelValue = stringResource(id = R.string.password),
                icon = Icons.Rounded.Lock
            )
            MyPasswordTextField(
                labelValue = stringResource(id = R.string.password_again),
                icon = Icons.Rounded.Lock
            )
            Spacer(modifier = Modifier.height(20.dp))
            CheckBoxComponent(value = stringResource(id = R.string.privacy_policy),
                onTextSelected = {
                    SuggestionBoxAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                })
        }
    }
}

@Preview
@Composable
fun SuggestionBoxAppPreview() {
    SignUpScreen()
}