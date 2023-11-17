package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.data.datasource.remote.service.ProductsService
import com.aldeadavila.suggestionbox.domain.model.Product
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class ProductsRemoteDataSourceImpl(private val productsService: ProductsService) :
    ProductsRemoteDataSource {
    override suspend fun findAll(): Response<List<Product>> = productsService.findAll()

    override suspend fun findByCategory(idCategory: String): Response<List<Product>> =
        productsService.findByCategory(idCategory)

    override suspend fun create(product: Product, files: List<File>): Response<Product> {

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


        val nameData = product.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = product.description.toRequestBody(contentType.toMediaTypeOrNull())
        val idCategoryData = product.idCategory.toRequestBody(contentType.toMediaTypeOrNull())
        val priceData = product.price.toString().toRequestBody(contentType.toMediaTypeOrNull())

        return productsService.create(
            images,
            nameData,
            descriptionData,
            idCategoryData,
            priceData
        )
    }

    override suspend fun updateWithImage(
        id: String,
        product: Product,
        files: List<File>?
    ): Response<Product> {
        val images = arrayOfNulls<MultipartBody.Part>(files?.size ?: 0)
        val contentType = "text/plain"
        val imagesToUpdate = arrayOfNulls<RequestBody>(product.imagesToUpdate?.size ?: 0)

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

        product.imagesToUpdate?.forEachIndexed { index, position ->
            imagesToUpdate[index] =
                position.toString().toRequestBody(contentType.toMediaTypeOrNull())
        }

        val nameData = product.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = product.description.toRequestBody(contentType.toMediaTypeOrNull())
        val idCategoryData = product.idCategory.toRequestBody(contentType.toMediaTypeOrNull())
        val priceData = product.price.toString().toRequestBody(contentType.toMediaTypeOrNull())

        return productsService.updateWithImage(
            images,
            id,
            nameData,
            descriptionData,
            idCategoryData,
            priceData,
            imagesToUpdate
        )
    }

    override suspend fun update(id: String, product: Product): Response<Product> =
        productsService.update(
            id,
            product
        )

    override suspend fun detele(id: String): Response<Unit> = productsService.delete(id)
}