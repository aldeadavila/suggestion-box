package com.aldeadavila.suggestionbox.data.repository

import android.net.Uri
import com.aldeadavila.suggestionbox.core.Config.USERS
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named


class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(USERS) private val storageUsersRef: StorageReference
    ): UsersRepository {
    override suspend fun createUser(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.user_id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(idUser: String): Flow<User> = callbackFlow{
        val snapshotListener = usersRef.document(idUser).addSnapshotListener { snapshot, _ ->

            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = HashMap()
            map["nickname"] = user.nickname
            map["profileImagePathUrl"] = user.profileImagePathUrl
            usersRef.document(user.user_id).update(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun saveImage(file: File, userId: String): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(userId + "/" + file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            return Response.Success(url.toString())
        }catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }


}