package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.domain.model.AuthUser
import com.aldeadavila.suggestionbox.domain.model.Resource
import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import com.aldeadavila.suggestionbox.domain.model.Resource.Success
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseRepository {

    override val currentUser get() = firebaseAuth.currentUser
    override fun firebaseSignIn(user: AuthUser): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading())
        firebaseAuth.signInWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
            trySend(Resource.Success("Login Successful"))
        }.addOnFailureListener {
            trySend(Resource.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }
    }

    override fun currentUserExist(): Flow<Boolean> {
        return flow {
            emit(firebaseAuth.currentUser != null)
        }
    }

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String
    ) = try {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        Success(true)
    } catch (e: Exception) {
        Resource.Error(e.message.toString())
    }

    override suspend fun sendEmailVerification() = try {
        firebaseAuth.currentUser?.sendEmailVerification()?.await()
        Success(true)
    } catch (e: Exception) {
        Resource.Error(e.message.toString())
    }


    override suspend fun sendPasswordResetEmail(email: String) = try {
        firebaseAuth.sendPasswordResetEmail(email).await()
        Success(true)
    } catch (e: Exception) {
        Resource.Error(e.message.toString())
    }

    override suspend fun reloadFirebaseUser() = try {
        firebaseAuth.currentUser?.reload()?.await()
        Success(true)
    } catch (e: Exception) {
        Resource.Error(e.message.toString())
    }

    override suspend fun revokeAccess() = try {
        firebaseAuth.currentUser?.delete()?.await()
        Success(true)
    } catch (e: Exception) {
        Resource.Error(e.message.toString())
    }

    override suspend fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }

    override fun signOut() = firebaseAuth.signOut()


}