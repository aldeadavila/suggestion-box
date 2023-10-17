package com.aldeadavila.suggestionbox.data

import android.net.Uri
import com.aldeadavila.suggestionbox.model.Response
import com.aldeadavila.suggestionbox.repository.AddImageToStorageResponse
import com.aldeadavila.suggestionbox.repository.AddImageUrlToFirestoreResponse
import com.aldeadavila.suggestionbox.repository.GetImageFromFirestoreResponse
import com.aldeadavila.suggestionbox.repository.ImageRepository
import com.aldeadavila.suggestionbox.utils.Constants.CREATED_AT
import com.aldeadavila.suggestionbox.utils.Constants.IMAGES
import com.aldeadavila.suggestionbox.utils.Constants.IMAGE_NAME
import com.aldeadavila.suggestionbox.utils.Constants.UID
import com.aldeadavila.suggestionbox.utils.Constants.URL
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
): ImageRepository {
    override suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse {
        return try {
            val downloadUrl = storage.reference.child(IMAGES).child(IMAGE_NAME)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Response.Success(downloadUrl)
        } catch (e:Exception) {
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
        } catch (e:Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getImageUrlFromFirestore(): GetImageFromFirestoreResponse {
        return try {
            val imageUrl = db.collection(IMAGES).document().get().await().getString(URL)
            Response.Success(imageUrl)
        } catch (e:Exception) {
            Response.Failure(e)
        }
    }

}