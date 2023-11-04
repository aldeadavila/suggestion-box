package com.aldeadavila.suggestionbox.domain.repository

import android.net.Uri
import com.aldeadavila.suggestionbox.domain.model.Response

typealias AddImageToStorageResponse = Response<Uri>
typealias AddImageUrlToFirestoreResponse = Response<Boolean>
typealias GetImageFromFirestoreResponse = Response<String>


interface ImageRepository {
    suspend fun addImageToStorageResponse(imageUri: Uri): AddImageToStorageResponse
    suspend fun addImageUrlToFirestore(download: Uri): AddImageUrlToFirestoreResponse
    suspend fun getImageFromFirestoreResponse(): GetImageFromFirestoreResponse
}