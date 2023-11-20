package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.data.datasource.local.SuggestionsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.SuggestionsRemoteDataSource
import com.aldeadavila.suggestionbox.data.mapper.toProduct
import com.aldeadavila.suggestionbox.data.mapper.toProductEntity
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.domain.util.ResponseToRequest
import com.aldeadavila.suggestionbox.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class SuggestionRepositoryImpl(
    private val suggestionsRemoteDataSource: SuggestionsRemoteDataSource,
    private val suggestionsLocalDataSource: SuggestionsLocalDataSource
    ):SuggestionRepository {
    override fun findAll(): Flow<Resource<List<Suggestion>>> = flow {
        suggestionsLocalDataSource.getSuggestions().collect() {
            it.run {
                val productsLocalMap = this.map {productEntity -> productEntity.toProduct() }
                try {
                    ResponseToRequest.send(suggestionsRemoteDataSource.findAll()).run {
                        when(this) {
                            is Resource.Succes -> {
                                val productsRemote = this.data

                                if(!isListEqual(productsRemote, productsLocalMap)) {
                                    suggestionsLocalDataSource.insertAll(productsRemote.map { product ->  product.toProductEntity()})
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

    override fun findByCategory(idCategory: String): Flow<Resource<List<Suggestion>>> = flow {
        suggestionsLocalDataSource.findByCategory(idCategory).collect() {
            it.run {
                val productsLocalMap = this.map {productEntity -> productEntity.toProduct() }
                try {
                    ResponseToRequest.send(suggestionsRemoteDataSource.findByCategory(idCategory)).run {
                        when(this) {
                            is Resource.Succes -> {
                                val productsRemote = this.data

                                if(!isListEqual(productsRemote, productsLocalMap)) {
                                    suggestionsLocalDataSource.insertAll(productsRemote.map { product ->  product.toProductEntity()})
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


    override suspend fun create(suggestion: Suggestion, files: List<File>): Resource<Suggestion> {

        ResponseToRequest.send(suggestionsRemoteDataSource.create(suggestion, files)).run {
            return when(this) {
                is Resource.Succes -> {
                    suggestionsLocalDataSource.create(this.data.toProductEntity())
                    Resource.Succes(this.data)
                }
                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

    override suspend fun updateWithImage(id:String, suggestion: Suggestion, files: List<File>?): Resource<Suggestion> {
        ResponseToRequest.send(suggestionsRemoteDataSource.updateWithImage(id, suggestion, files)).run {
            return when(this) {
                is Resource.Succes -> {
                    suggestionsLocalDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image1 = this.data.image1 ?: "",
                        image2 = this.data.image2 ?: "",
                        idUser = this.data.idUser,
                    )
                    Resource.Succes(this.data)
                }
                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

    override suspend fun update(id: String, suggestion: Suggestion): Resource<Suggestion> {
        ResponseToRequest.send(suggestionsRemoteDataSource.update(id, suggestion)).run {
            return when(this) {
                is Resource.Succes -> {
                    suggestionsLocalDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image1 = this.data.image1 ?: "",
                        image2 = this.data.image2 ?: "",
                        idUser = this.data.idUser,
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
        ResponseToRequest.send(suggestionsRemoteDataSource.detele(id)).run {
            return when(this) {
                is Resource.Succes -> {
                   suggestionsLocalDataSource.delete(id)
                    Resource.Succes(Unit)
                }
                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }


}