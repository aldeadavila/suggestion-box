package com.aldeadavila.suggestionbox.data.repository;

import com.aldeadavila.suggestionbox.core.Config.NEWS
import com.aldeadavila.suggestionbox.domain.model.News
import com.aldeadavila.suggestionbox.domain.model.Response
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

import com.aldeadavila.suggestionbox.domain.repository.NewsRepository;

import javax.inject.Inject;
import javax.inject.Named;

class NewsRepositoryImpl @Inject constructor(
    @Named(NEWS) private val newsRef: CollectionReference,
    @Named(NEWS) private val storageNewsRef: StorageReference,
): NewsRepository {
    override fun getNews(): Flow<Response<List<News>>> = callbackFlow {
        val snapshotListener = newsRef.addSnapshotListener { snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val newsResponse = if (snapshot != null) {
                    val news = snapshot.toObjects(News::class.java)
                    val sortNews = news.sortedWith(compareBy({it.created_at}))
                    Response.Success(sortNews)
                } else {
                    Response.Failure(Exception(e?.message ?: "Error desconocido"))
                }
                trySend(newsResponse)
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}
