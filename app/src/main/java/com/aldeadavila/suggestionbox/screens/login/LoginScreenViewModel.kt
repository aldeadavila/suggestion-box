package com.aldeadavila.suggestionbox.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.model.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithGoogleCredential(credential: AuthCredential, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithCredential(credential)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d("login", "Logueado con Google correcto")
                            home()
                        }
                    }.addOnFailureListener{
                        Log.d("login", "Fallo al loguear con Google")
                    }
            }
            catch (ex:Exception) {
                Log.d("login", "Excepción  al loguear con Google: ${ex.localizedMessage}")
            }

        }

    fun signInWithEmailAndPassword(email:String, password:String, home: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("login", "login succesfully in firebase")
                        home()
                    } else {
                        Log.d("login", "login in firebase wrong: ${task.result.toString()}")
                    }

                }
        } catch (ex:Exception) {
            Log.d("login", "login error: ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ){
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        val displayName =
                            task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    } else {
                        Log.d("create", "error create user ${task.result}")
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        //val user = mutableMapOf<String, Any>()

        //user["user_id"] = userId.toString()
        //user["display_name"] = displayName.toString()

        val user = User(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Lo difícil ya pasó",
            profession = "Android dev",
            id = null
        ).toMap()

        FirebaseFirestore
            .getInstance()
            .collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("user", "Usuario creado ${it.id}")
            }.addOnFailureListener{
                Log.d("user", "Error al crear usuario ${it}")
            }
    }

}