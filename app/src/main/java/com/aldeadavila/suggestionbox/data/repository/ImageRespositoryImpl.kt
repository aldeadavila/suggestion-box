package com.aldeadavila.suggestionbox.data.repository

import android.net.Uri
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.repository.AddImageToStorageResponse
import com.aldeadavila.suggestionbox.domain.repository.AddImageUrlToFirestoreResponse
import com.aldeadavila.suggestionbox.domain.repository.GetImageFromFirestoreResponse
import com.aldeadavila.suggestionbox.domain.repository.ImageRepository
import com.aldeadavila.suggestionbox.util.Constants.CREATED_AT
import com.aldeadavila.suggestionbox.util.Constants.IMAGES
import com.aldeadavila.suggestionbox.util.Constants.IMAGE_NAME
import com.aldeadavila.suggestionbox.util.Constants.UID
import com.aldeadavila.suggestionbox.util.Constants.URL
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageRespositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
): ImageRepository {
    override suspend fun addImageToStorageResponse(imageUri: Uri): AddImageToStorageResponse {
        return try {
            val downloadUrl = storage.reference.child(IMAGES).child(IMAGE_NAME)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Response.Success(downloadUrl)
        }
        catch (e:Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun addImageUrlToFirestore(download: Uri): AddImageUrlToFirestoreResponse {
        return try {
           db.collection(IMAGES).document(UID).set(mapOf(
               URL to download,
                CREATED_AT to FieldValue.serverTimestamp()
           )).await()
            Response.Success(true)
        }
        catch (e:Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getImageFromFirestoreResponse(): GetImageFromFirestoreResponse {
        return try {
            val imageUrl = db.collection(IMAGES).document(UID).get().await().getString(URL)
            Response.Success(imageUrl)
        }
        catch (e:Exception) {
            Response.Failure(e)
        }
    }
}