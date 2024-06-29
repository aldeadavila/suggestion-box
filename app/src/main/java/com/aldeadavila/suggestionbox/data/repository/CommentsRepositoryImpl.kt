package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.core.Config
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.repository.CommentsRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class CommentsRepositoryImpl @Inject constructor(
    @Named(Config.COMMENTS) private val commentsRef: CollectionReference,
) : CommentsRepository {
    override fun findAll(): Flow<Response<List<Comment>>> {
        TODO("Not yet implemented")
    }

    override fun findBySuggestion(idSuggestion: String): Flow<Response<List<Comment>>> =
        callbackFlow {
            val snapshopListener = commentsRef.whereEqualTo("suggestion_id", idSuggestion)
                .addSnapshotListener { snapshot, e ->
                    GlobalScope.launch(Dispatchers.IO) {
                        val commentsResponse = if (snapshot != null) {
                            val comments = snapshot.toObjects(Comment::class.java)
                            Response.Success(comments)
                        } else {
                            Response.Failure(Exception(e?.message ?: "Error desconocido"))
                        }
                        trySend(commentsResponse)
                    }
                }
            awaitClose {
                snapshopListener.remove()
            }
        }

    override fun findByUser(idUser: String): Flow<Response<List<Comment>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createComment(comment: Comment): Response<Boolean> {
        return try {
            val document = commentsRef.document()
            comment.comment_id = document.id
            commentsRef.document(document.id).set(comment).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(id: String, comment: Comment): Response<Comment> {
        return try {
            val map: MutableMap<String, Any> = HashMap()
            map["content"] = comment.content
            commentsRef.document(id).update(map).await()
            Response.Success(comment)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun delete(id: String): Response<Unit> {
        TODO("Not yet implemented")
    }
}