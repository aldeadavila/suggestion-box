package com.aldeadavila.suggestionbox.presentation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.core.Config.WEB_CLIENT_ID
import com.aldeadavila.suggestionbox.presentation.navigation.Graph
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID


@Composable
fun GoogleSignInButton(navHostController: NavHostController) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val auth = Firebase.auth
    // val startDestination = if (auth.currentUser == null) HomeSc

    val onClick: () -> Unit = {
        val credentialManager = CredentialManager.create(context)

        val randNonce = UUID.randomUUID().toString()
        val bytes = randNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashNonce = digest.fold("") { str, it -> str + "%02x".format(it) }

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(WEB_CLIENT_ID)
            .setAutoSelectEnabled(true)
            .setNonce(hashNonce)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context
                )

                val credential = result.credential
                val googleIdTokenCredential = GoogleIdTokenCredential
                    .createFrom(credential.data)
                val googleIdToken = googleIdTokenCredential.idToken
                val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                auth.signInWithCredential(firebaseCredential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navHostController.popBackStack(Graph.AUTH, inclusive = true)
                        navHostController.navigate(route = Graph.HOME)
                    }
                }
                Toast.makeText(context, "Has entrado con tu cuenta de google", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: GetCredentialException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                Log.d(
                    "GoogleSignInButton",
                    "Image: ${e.printStackTrace()}"
                )
            } catch (e: GoogleIdTokenParsingException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                Log.d(
                    "GoogleSignInButton",
                    "trace: ${e.printStackTrace()}"
                )
            } catch (e: Exception) {
                Log.d(
                    "GoogleSignInButton",
                    "generic: ${e.printStackTrace()}"
                )            }

        }

    }
    Image(
        modifier = Modifier.clickable { onClick() },
        painter = painterResource(id = R.drawable.android_dark_rd_ctn),
        contentDescription = "Icono de Google",
        alignment = Alignment.Center
    )
}