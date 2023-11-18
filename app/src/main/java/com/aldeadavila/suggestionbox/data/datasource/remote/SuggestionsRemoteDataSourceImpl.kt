package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.data.datasource.remote.service.SuggestionService
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class SuggestionsRemoteDataSourceImpl(private val suggestionService: SuggestionService) :
    SuggestionsRemoteDataSource {
    override suspend fun findAll(): Response<List<Suggestion>> = suggestionService.findAll()

    override suspend fun findByCategory(idCategory: String): Response<List<Suggestion>> =
        suggestionService.findByCategory(idCategory)

    override suspend fun create(suggestion: Suggestion, files: List<File>): Response<Suggestion> {

        val images = arrayOfNulls<MultipartBody.Part>(files.size)
        val contentType = "text/plain"

        files.forEachIndexed { index, file ->
            val connection = file.toURI().toURL().openConnection()
            val mimeType = connection.contentType // para saber el tipo del archivo
            val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
            images[index] = MultipartBody.Part.createFormData(
                "files[]",
                file.name,
                requestFile
            )

        }


        val nameData = suggestion.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = suggestion.description.toRequestBody(contentType.toMediaTypeOrNull())
        val idCategoryData = suggestion.idCategory.toRequestBody(contentType.toMediaTypeOrNull())
        val priceData = suggestion.price.toString().toRequestBody(contentType.toMediaTypeOrNull())

        return suggestionService.create(
            images,
            nameData,
            descriptionData,
            idCategoryData,
            priceData
        )
    }

    override suspend fun updateWithImage(
        id: String,
        suggestion: Suggestion,
        files: List<File>?
    ): Response<Suggestion> {
        val images = arrayOfNulls<MultipartBody.Part>(files?.size ?: 0)
        val contentType = "text/plain"
        val imagesToUpdate = arrayOfNulls<RequestBody>(suggestion.imagesToUpdate?.size ?: 0)

        files?.forEachIndexed { index, file ->
            val connection = file.toURI().toURL().openConnection()
            val mimeType = connection.contentType // para saber el tipo del archivo
            val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
            images[index] = MultipartBody.Part.createFormData(
                "files[]",
                file.name,
                requestFile
            )

        }

        suggestion.imagesToUpdate?.forEachIndexed { index, position ->
            imagesToUpdate[index] =
                position.toString().toRequestBody(contentType.toMediaTypeOrNull())
        }

        val nameData = suggestion.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = suggestion.description.toRequestBody(contentType.toMediaTypeOrNull())
        val idCategoryData = suggestion.idCategory.toRequestBody(contentType.toMediaTypeOrNull())
        val priceData = suggestion.price.toString().toRequestBody(contentType.toMediaTypeOrNull())

        return suggestionService.updateWithImage(
            images,
            id,
            nameData,
            descriptionData,
            idCategoryData,
            priceData,
            imagesToUpdate
        )
    }

    override suspend fun update(id: String, suggestion: Suggestion): Response<Suggestion> =
        suggestionService.update(
            id,
            suggestion
        )

    override suspend fun detele(id: String): Response<Unit> = suggestionService.delete(id)
}