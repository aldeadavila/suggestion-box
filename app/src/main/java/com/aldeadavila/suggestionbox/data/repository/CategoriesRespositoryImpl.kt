package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.data.datasource.local.CategoriesLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.CategoriesRemoteDatasource
import com.aldeadavila.suggestionbox.data.mapper.toCategory
import com.aldeadavila.suggestionbox.data.mapper.toCategoryEntity
import com.aldeadavila.suggestionbox.domain.model.Category
import com.aldeadavila.suggestionbox.domain.repository.CategoriesRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.domain.util.ResponseToRequest
import com.aldeadavila.suggestionbox.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class CategoriesRespositoryImpl(
    private val remoteDatasource: CategoriesRemoteDatasource,
    private val localDataSource: CategoriesLocalDataSource
) : CategoriesRepository {
    override suspend fun create(category: Category, file: File): Resource<Category> {
        ResponseToRequest.send(
            remoteDatasource.create(
                category,
                file
            )
        ).run {
            return when (this) {
                is Resource.Succes -> {
                    localDataSource.create(this.data.toCategoryEntity())
                    Resource.Succes(this.data)
                }

                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

    override fun getCategories(): Flow<Resource<List<Category>>> = flow {

        localDataSource.getCategories().collect {
            it.run {
                /*if (this.isNotEmpty()) {
                    emit(Resource.Succes(this.map{ categoryEntity -> categoryEntity.toCategory()  }))
                }*/
                val categoryLocalMap = this.map { categoryEntity -> categoryEntity.toCategory() }
                try {

                    ResponseToRequest.send(remoteDatasource.getCategories()).run {
                        when (this) {
                            is Resource.Succes -> {
                                val categoriesRemote = this.data

                                if (!isListEqual(
                                        categoriesRemote,
                                        categoryLocalMap
                                    )
                                ) {
                                    localDataSource.insertAll(categoriesRemote.map { category -> category.toCategoryEntity() })
                                }

                                emit(Resource.Succes(categoriesRemote))
                            }

                            is Resource.Failure -> {
                                emit(Resource.Succes(categoryLocalMap))
                            }

                            else -> {
                                emit(Resource.Succes(categoryLocalMap))
                            }
                        }
                    }

                } catch (e: Exception) {
                    emit(Resource.Succes(categoryLocalMap))
                }
            }
        }

    }.flowOn(Dispatchers.IO)


    override suspend fun update(id: String, category: Category): Resource<Category> {

        ResponseToRequest.send(
            remoteDatasource.update(
                id,
                category
            )
        ).run {
            return when (this) {
                is Resource.Succes -> {
                    localDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image = this.data.image ?: ""
                    )
                    Resource.Succes(this.data)
                }

                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

    override suspend fun updateWithImage(
        id: String,
        category: Category,
        file: File
    ): Resource<Category> {
        ResponseToRequest.send(
            remoteDatasource.updateWithImage(
                id,
                category,
                file
            )
        ).run {
            return when (this) {
                is Resource.Succes -> {
                    localDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image = this.data.image ?: ""
                    )
                    Resource.Succes(this.data)
                }

                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

    override suspend fun delete(id: String): Resource<Unit> {
        ResponseToRequest.send(remoteDatasource.delete(id)).run {
            return when (this) {
                is Resource.Succes -> {
                    localDataSource.delete(id)
                    Resource.Succes(Unit)
                }

                else -> {
                    Resource.Failure("Error desconocido")
                }
            }
        }
    }

}