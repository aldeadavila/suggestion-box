package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.data.datasource.local.ProductsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.ProductsRemoteDataSource
import com.aldeadavila.suggestionbox.data.mapper.toProduct
import com.aldeadavila.suggestionbox.data.mapper.toProductEntity
import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.domain.repository.ProductsRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.domain.util.ResponseToRequest
import com.aldeadavila.suggestionbox.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class ProductsRepositoryImpl(
    private val productsRemoteDataSource: ProductsRemoteDataSource,
    private val productsLocalDataSource: ProductsLocalDataSource
    ):ProductsRepository {
    override fun findAll(): Flow<Resource<List<Product>>> = flow {
        productsLocalDataSource.getProducts().collect() {
            it.run {
                val productsLocalMap = this.map {productEntity -> productEntity.toProduct() }
                try {
                    ResponseToRequest.send(productsRemoteDataSource.findAll()).run {
                        when(this) {
                            is Resource.Succes -> {
                                val productsRemote = this.data

                                if(!isListEqual(productsRemote, productsLocalMap)) {
                                    productsLocalDataSource.insertAll(productsRemote.map { product ->  product.toProductEntity()})
                                }

                                emit(Resource.Succes(productsRemote))
                            }
                            else -> {
                                emit(Resource.Succes(productsLocalMap))
                            }
                        }
                    }
                }catch (e: Exception) {
                    emit(Resource.Succes(productsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun findByCategory(idCategory: String): Flow<Resource<List<Product>>> = flow {
        productsLocalDataSource.findByCategory(idCategory).collect() {
            it.run {
                val productsLocalMap = this.map {productEntity -> productEntity.toProduct() }
                try {
                    ResponseToRequest.send(productsRemoteDataSource.findByCategory(idCategory)).run {
                        when(this) {
                            is Resource.Succes -> {
                                val productsRemote = this.data

                                if(!isListEqual(productsRemote, productsLocalMap)) {
                                    productsLocalDataSource.insertAll(productsRemote.map { product ->  product.toProductEntity()})
                                }

                                emit(Resource.Succes(productsRemote))
                            }
                            else -> {
                                emit(Resource.Succes(productsLocalMap))
                            }
                        }
                    }
                }catch (e: Exception) {
                    emit(Resource.Succes(productsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun create(product: Product, files: List<File>): Resource<Product> {

        ResponseToRequest.send(productsRemoteDataSource.create(product, files)).run {
            return when(this) {
                is Resource.Succes -> {
                    productsLocalDataSource.create(this.data.toProductEntity())
                    Resource.Succes(this.data)
                }
                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

    override suspend fun updateWithImage(id:String, product: Product, files: List<File>?): Resource<Product> {
        ResponseToRequest.send(productsRemoteDataSource.updateWithImage(id, product, files)).run {
            return when(this) {
                is Resource.Succes -> {
                    productsLocalDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image1 = this.data.image1 ?: "",
                        image2 = this.data.image2 ?: "",
                        price = this.data.price,
                    )
                    Resource.Succes(this.data)
                }
                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

    override suspend fun update(id: String, product: Product): Resource<Product> {
        ResponseToRequest.send(productsRemoteDataSource.update(id, product)).run {
            return when(this) {
                is Resource.Succes -> {
                    productsLocalDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image1 = this.data.image1 ?: "",
                        image2 = this.data.image2 ?: "",
                        price = this.data.price,
                    )
                    Resource.Succes(this.data)
                }
                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }



    override suspend fun detele(id: String): Resource<Unit> {
        ResponseToRequest.send(productsRemoteDataSource.detele(id)).run {
            return when(this) {
                is Resource.Succes -> {
                   productsLocalDataSource.delete(id)
                    Resource.Succes(Unit)
                }
                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }


}