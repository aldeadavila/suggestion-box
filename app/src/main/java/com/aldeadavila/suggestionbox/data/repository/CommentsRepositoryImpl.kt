package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.data.datasource.local.CommentsLocalDataSource
import com.aldeadavila.suggestionbox.data.datasource.remote.CommentsRemoteDataSource
import com.aldeadavila.suggestionbox.data.mapper.toComment
import com.aldeadavila.suggestionbox.data.mapper.toCommentEntity
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.aldeadavila.suggestionbox.domain.util.ResponseToRequest
import com.aldeadavila.suggestionbox.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CommentsRepositoryImpl(
    private val commentsRemoteDataSource: CommentsRemoteDataSource,
    private val commentsLocalDataSource: CommentsLocalDataSource
) : CommentsRepository {
    override fun findAll(): Flow<Resource<List<Comment>>> = flow {
        commentsLocalDataSource.getComment().collect {
            it.run {
                val commentsLocalMap = this.map { commentEntity -> commentEntity.toComment() }
                try {
                    ResponseToRequest.send(commentsRemoteDataSource.findAll()).run {
                        when (this) {
                            is Resource.Succes -> {
                                val commentsRemote = this.data

                                if (!isListEqual(commentsRemote, commentsLocalMap)) {
                                    commentsLocalDataSource.insertAll(commentsRemote.map { comment -> comment.toCommentEntity() })
                                }
                                emit(Resource.Succes(commentsRemote))
                            }
                            else -> {
                                emit(Resource.Succes(commentsLocalMap))
                            }
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Succes(commentsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun findBySuggestion(idSuggestion: String): Flow<Resource<List<Comment>>> = flow {
        commentsLocalDataSource.findBySuggestion(idSuggestion).collect() {
            it.run {
                val commentsLocalMap = this.map { commentEntity -> commentEntity.toComment() }
                try {
                    ResponseToRequest.send(commentsRemoteDataSource.findBySuggestion(idSuggestion)).run {
                        when(this) {
                            is Resource.Succes -> {
                                val commentsRemote = this.data

                                if(!isListEqual(commentsRemote, commentsLocalMap)) {
                                    commentsLocalDataSource.insertAll(commentsRemote.map { comment -> comment.toCommentEntity() })
                                }
                                emit(Resource.Succes(commentsRemote))
                            }
                            else -> {
                                emit(Resource.Succes(commentsLocalMap))
                            }
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Succes(commentsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun findByUser(idUser: String): Flow<Resource<List<Comment>>> = flow {
        commentsLocalDataSource.findByUser(idUser).collect() {
            it.run {
                val commentsLocalMap = this.map { commentEntity -> commentEntity.toComment() }
                try {
                    ResponseToRequest.send(commentsRemoteDataSource.findByUser(idUser)).run {
                        when(this) {
                            is Resource.Succes -> {
                                val commentsRemote = this.data

                                if(!isListEqual(commentsRemote, commentsLocalMap)) {
                                    commentsLocalDataSource.insertAll(commentsRemote.map { comment -> comment.toCommentEntity() })
                                }
                                emit(Resource.Succes(commentsRemote))
                            }
                            else -> {
                                emit(Resource.Succes(commentsLocalMap))
                            }
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Succes(commentsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun create(comment: Comment): Resource<Comment> {
        ResponseToRequest.send(commentsRemoteDataSource.create(comment)).run {
            return when(this) {
                is Resource.Succes -> {
                    commentsLocalDataSource.create(this.data.toCommentEntity())
                    Resource.Succes(this.data)
                }
                else -> {
                    Resource.Failure("Error al crear comentarios")
                }
            }
        }
    }

    override suspend fun update(id: String, comment: Comment): Resource<Comment> {
       ResponseToRequest.send(commentsRemoteDataSource.update(id, comment)).run {
           return when(this) {
               is Resource.Succes -> {
                   commentsLocalDataSource.update(
                       id = this.data.id ?: "",
                       content = this.data.content,
                   )
                   Resource.Succes(this.data)
               }
               else -> {
                   Resource.Failure("Error al actualizar el comentario")
               }
           }
       }
    }

    override suspend fun detele(id: String): Resource<Unit> {
        ResponseToRequest.send(commentsRemoteDataSource.detele(id)).run {
            return when(this) {
                is Resource.Succes -> {
                    commentsLocalDataSource.delete(id)
                    Resource.Succes(Unit)
                }
                else -> {
                    Resource.Failure("Error al borrar el comentario")
                }
            }
        }
    }
}