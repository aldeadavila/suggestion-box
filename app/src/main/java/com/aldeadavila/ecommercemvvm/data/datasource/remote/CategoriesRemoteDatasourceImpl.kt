package com.aldeadavila.suggestionbox.data.datasource.remote

import android.util.Log
import com.aldeadavila.suggestionbox.data.datasource.remote.service.CategoryService
import com.aldeadavila.suggestionbox.domain.model.Category
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class CategoriesRemoteDatasourceImpl(private val categoryService: CategoryService) :
    CategoriesRemoteDatasource {
    override suspend fun create(category: Category, file: File): Response<Category> {
        val connection = file.toURI().toURL().openConnection()
        val mimeType = connection.contentType // para saber el tipo del archivo
        val contentType = "text/plain"
        val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestFile
        )
        val nameData = category.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = category.description.toRequestBody(contentType.toMediaTypeOrNull())

        return categoryService.create(
            fileFormData,
            nameData,
            descriptionData
        )
    }

    override suspend fun getCategories(): Response<List<Category>> = categoryService.getCategories()

    override suspend fun update(id: String, category: Category): Response<Category> =
        categoryService.update(
            id,
            category
        )

    override suspend fun updateWithImage(
        id: String,
        category: Category,
        file: File
    ): Response<Category> {

        val connection = file.toURI().toURL().openConnection()
        val mimeType = connection.contentType // para saber el tipo del archivo
        val contentType = "text/plain"
        val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestFile
        )
        val nameData = category.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = category.description.toRequestBody(contentType.toMediaTypeOrNull())


        Log.d(
            "CategoriesRemoteDatasourceImpl",
            "fileFormData ${fileFormData} id ${id} nameData ${nameData} descriptionData ${descriptionData}"
        )
        return categoryService.updateWithImage(
            fileFormData,
            id,
            nameData,
            descriptionData
        )

    }

    override suspend fun delete(id: String): Response<Unit> = categoryService.delete(id)


}