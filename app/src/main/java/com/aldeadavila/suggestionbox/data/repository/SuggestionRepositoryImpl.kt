package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.data.datasource.local.SuggestionsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.SuggestionsRemoteDataSource
import com.aldeadavila.suggestionbox.data.mapper.toSuggestion
import com.aldeadavila.suggestionbox.data.mapper.toSuggestionEntity
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
                val suggestionsLocalMap = this.map { suggestionEntity -> suggestionEntity.toSuggestion() }
                try {
                    ResponseToRequest.send(suggestionsRemoteDataSource.findAll()).run {
                        when(this) {
                            is Resource.Succes -> {
                                val suggestionRemote = this.data

                                if(!isListEqual(suggestionRemote, suggestionsLocalMap)) {
                                    suggestionsLocalDataSource.insertAll(suggestionRemote.map { suggestion ->  suggestion.toSuggestionEntity()})
                                }

                                emit(Resource.Succes(suggestionRemote))
                            }
                            else -> {
                                emit(Resource.Succes(suggestionsLocalMap))
                            }
                        }
                    }
                }catch (e: Exception) {
                    emit(Resource.Succes(suggestionsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun findByCategory(idCategory: String): Flow<Resource<List<Suggestion>>> = flow {
        suggestionsLocalDataSource.findByCategory(idCategory).collect() {
            it.run {
                val suggestionsLocalMap = this.map { suggestionEntity -> suggestionEntity.toSuggestion() }
                try {
                    ResponseToRequest.send(suggestionsRemoteDataSource.findByCategory(idCategory)).run {
                        when(this) {
                            is Resource.Succes -> {
                                val suggestionsRemote = this.data

                                if(!isListEqual(suggestionsRemote, suggestionsLocalMap)) {
                                    suggestionsLocalDataSource.insertAll(suggestionsRemote.map { suggestion ->  suggestion.toSuggestionEntity()})
                                }

                                emit(Resource.Succes(suggestionsRemote))
                            }
                            else -> {
                                emit(Resource.Succes(suggestionsLocalMap))
                            }
                        }
                    }
                }catch (e: Exception) {
                    emit(Resource.Succes(suggestionsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun create(suggestion: Suggestion, files: List<File>): Resource<Suggestion> {

        ResponseToRequest.send(suggestionsRemoteDataSource.create(suggestion, files)).run {
            return when(this) {
                is Resource.Succes -> {
                    suggestionsLocalDataSource.create(this.data.toSuggestionEntity())
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