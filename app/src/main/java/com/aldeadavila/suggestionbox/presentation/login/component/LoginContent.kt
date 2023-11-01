package com.aldeadavila.suggestionbox.presentation.login.component

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.common.NormalTextComponent
import com.aldeadavila.suggestionbox.presentation.common.SmallTextComponent
import com.aldeadavila.suggestionbox.presentation.common.TitleTextComponent
import com.aldeadavila.suggestionbox.domain.model.AuthUser
import com.aldeadavila.suggestionbox.navigation.screen.AppScreen
import com.aldeadavila.suggestionbox.presentation.common.AuthenticationField
import com.aldeadavila.suggestionbox.presentation.login.LoginViewModel
import com.aldeadavila.suggestionbox.presentation.login.OneTapSignInViewModel
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins
import com.aldeadavila.suggestionbox.util.Constants.FORGOT_PASSWORD
import com.aldeadavila.suggestionbox.util.Constants.LOGIN_GOOGLE
import com.aldeadavila.suggestionbox.util.Constants.NO_ACCOUNT
import com.aldeadavila.suggestionbox.util.Constants.SERVER_CLIENT_ID
import com.aldeadavila.suggestionbox.util.Constants.SIGN_IN_BUTTON
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginContent(
    padding: PaddingValues,
    oneTapSignInViewModel: OneTapSignInViewModel,
    viewModel: LoginViewModel,
    navController: NavHostController,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit
) {
    val googleState = oneTapSignInViewModel.googleSingInState.collectAsState()


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken!!, null)
                oneTapSignInViewModel.signInWithGoogleCredentials(credentials, user = AuthUser())
            } catch (it: ApiException) {
                print(it)
            }
        }


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.password_visible)
    } else {
        painterResource(id = R.drawable.password_toggle)
    }
    val signInState = viewModel.signInState.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
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

        AuthenticationField(
            text = email,
            placeHolder = "Email",
            isPasswordTextField = false,
            onValueChange = { email = it },
            errorMsg = "*Enter valid email address",
            trailingIcon = {
                if (email.isNotBlank()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Text"
                        )

                    }
                }
            })
        Spacer(modifier = Modifier.height(16.dp))
        AuthenticationField(
            text = password,
            placeHolder = "Password",
            isPasswordTextField = !passwordVisibility,
            onValueChange = { password = it },
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
        Text(
            text = FORGOT_PASSWORD,
            color = md_theme_light_primary,
            modifier = Modifier
                .clickable {
                    navigateToForgotPasswordScreen()
                }
                .padding(top = 10.dp),
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
                scope.launch(Dispatchers.Main) {
                    viewModel.loginUser(
                        AuthUser(
                            email, password
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Box(modifier = Modifier
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), horizontalArrangement = Arrangement.Center

            ) {

                Text(
                    text = LOGIN_GOOGLE,
                    color = Color.Black,
                    modifier = Modifier
                        .clickable {
                            val gso = GoogleSignInOptions
                                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(SERVER_CLIENT_ID)
                                .requestEmail()
                                .build()
                            val googleSignInClient = GoogleSignIn.getClient(context, gso)
                            launcher.launch(googleSignInClient.signInIntent)
                        }
                        .padding(top = 10.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        fontFamily = poppins
                    ),
                )

                IconButton(
                    onClick = {
                        val gso = GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(SERVER_CLIENT_ID)
                            .requestEmail()
                            .build()
                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        launcher.launch(googleSignInClient.signInIntent)
                    }) {
                    Icon(
                        modifier = Modifier.size(50.dp),

                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Icon", tint = Color.Unspecified
                    )
                }
            }
        }


        Text(
            text = NO_ACCOUNT,
            color = Color.Black,
            modifier = Modifier
                .clickable {
                    navigateToSignUpScreen()
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
            painter = painterResource(id = R.drawable.escudo_aldeadavila),

            contentDescription = "Escudo de Aldeadávila",
            modifier = Modifier
                .padding(top = 10.dp)
                .size(150.dp)
        )
        SmallTextComponent(
            stringResource(id = R.string.guest),
            modifier = Modifier.padding(top=30.dp),
            color = Color.Black
        )
    }

    LaunchedEffect(key1 = signInState.value?.error) {
        scope.launch(Dispatchers.Main) {
            if (signInState.value?.error?.isNotEmpty() == true) {
                val error = signInState.value?.error
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }

    LaunchedEffect(key1 = signInState.value?.isSignedIn) {
        if (signInState.value?.isSignedIn?.isNotEmpty() == true) {
            navController.popBackStack()
            val successful = signInState.value?.isSignedIn
            Toast.makeText(context, successful, Toast.LENGTH_LONG).show()
            navController.navigate(
                AppScreen.Home.route
            )
        }
    }

    LaunchedEffect(key1 = googleState.value.success) {
        if (googleState.value.success != null) {
            // navController.popBackStack()
            navController.navigate(
                AppScreen.Home.route
            )
            val successful = googleState.value.success.toString()
            Toast.makeText(context, "successful", Toast.LENGTH_LONG).show()

        }
    }
    
}