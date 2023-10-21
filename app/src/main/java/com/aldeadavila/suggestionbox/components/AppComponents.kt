package com.aldeadavila.suggestionbox.components



import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_secondary99
import com.aldeadavila.suggestionbox.ui.theme.poppins

@Composable
fun NormalTextComponent(value:String) {
    Text(
      //  modifier = Modifier.padding(horizontal = 50.dp),
        text = value,
        color = Color.Black,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontFamily = poppins
        ),

        )
}

@Composable
fun TitleTextComponent(value:String) {
    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 25.dp),
        text = value,
        color = md_theme_light_primary,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = poppins
            ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue: String, icon: ImageVector) {

    var textValue by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = md_theme_light_primary,
            focusedLabelColor = md_theme_light_primary,
            cursorColor = md_theme_light_primary,
            containerColor = md_theme_secondary99

        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue,
        onValueChange = { textValue = it },
        leadingIcon = {
            Icon(icon, contentDescription = "")
        }

    )
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPasswordTextField(labelValue: String, icon: ImageVector) {

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = md_theme_light_primary,
            focusedLabelColor = md_theme_light_primary,
            cursorColor = md_theme_light_primary,
            containerColor = md_theme_secondary99

        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password,
        onValueChange = { password = it },
        leadingIcon = {
            Icon(icon, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if(passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if(passwordVisible) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

    )

}

@Composable
fun CheckBoxComponent(value:String, onTextSelected: (String) -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
           // .heightIn(56.dp)
           // .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value, onCheckedChange = {
            checkedState.value  = !checkedState.value
        })

        ClickableTextComponent(value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "Si continua usted acepta nuestra "
    val privacyPolicyText = "política de privacidad"
    val and = " y "
    val termsAndConditionsText = "términos de uso."

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle( color = md_theme_light_primary, fontWeight = FontWeight.Bold)){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(and)
        withStyle(style = SpanStyle( color = md_theme_light_primary, fontWeight = FontWeight.Bold)){
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")
                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }
    } )
}