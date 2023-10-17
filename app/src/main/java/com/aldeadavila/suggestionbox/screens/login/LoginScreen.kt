package com.aldeadavila.suggestionbox.screens.login


import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.screens.login.LoginScreenViewModel
import com.aldeadavila.suggestionbox.navigation.ScreenRoutes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(navController: NavController,
                viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {
    //true = login; false = create
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)
    }
    val token = "746006924239-q08v583vl9d0p993nenmsr5q0c09i3pc.apps.googleusercontent.com"
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult())
    {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInWithGoogleCredential(credential){
                navController.navigate(ScreenRoutes.ImageScreen.name)
            }
        } catch (ex:Exception){
            Log.d("login", "GoogleSignIn lanzó una excepción")
        }
    }
    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showLoginForm.value) {
                Text(text = "Inicia sesión")
                UserForm( isCreateAccount = false)
                {
                    email, password ->
                    Log.d("login", "Logueando con $email y $password")
                    viewModel.signInWithEmailAndPassword(email, password){
                        navController.navigate(ScreenRoutes.ImageScreen.name)
                    }
                }
            } else {
                Text(text = "Crea una cuenta")
                UserForm( isCreateAccount = true)
                {
                        email, password ->
                    Log.d("create account", "Creando cuenta con $email y $password")
                    viewModel.createUserWithEmailAndPassword(email, password){
                        navController.navigate(ScreenRoutes.ImageScreen.name)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                val text1 =
                    if (showLoginForm.value) "¿No tienes cuenta?"
                    else "¿Ya tienes cuenta?"
                val text2 =
                    if (showLoginForm.value) "Regístrate"
                    else "Inicia sesión"
                Text(text = text1)
                Text(text = text2,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(start = 5.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                               val opciones = GoogleSignInOptions.Builder(
                                   GoogleSignInOptions.DEFAULT_SIGN_IN
                               ).requestIdToken(token)
                                   .requestEmail()
                                   .build()
                        val googleSingInClient = GoogleSignIn.getClient(context, opciones)
                        launcher.launch(googleSingInClient.signInIntent)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Login con Google",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                )
                Text(
                    text = "Login con Google",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                    )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(isCreateAccount: Boolean,
             onDone: (String, String)-> Unit = {email, pwd ->}
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val valido = remember(email.value, password.value){
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        EmailInput(emailState = email)
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        SubmitButton(
            textId = if (isCreateAccount) "Crear cuenta" else "login",
            inputValido = valido
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(textId: String,
                 inputValido: Boolean,
                 onClic: ()->Unit

) {
    Button(onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido
    ) {
        Text(text = textId,
            modifier = Modifier
                .padding(5.dp)
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(passwordState: MutableState<String>, 
                  labelId: String, 
                  passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if(passwordVisible.value) VisualTransformation.None
    else PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()) {
                PasswordVisibleIcon(passwordVisible)
            } else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    //librería de iconos extendidos
    //implementation 'androidx.compose.material:material-icons-extended:1.3.1'
    val image =
        if (passwordVisible.value) Icons.Default.VisibilityOff
        else Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun EmailInput(emailState: MutableState<String>, labelId: String = "Email") {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(valueState: MutableState<String>,
               labelId: String,
               isSingleLine: Boolean = true,
               keyboardType: KeyboardType)
{
    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}
