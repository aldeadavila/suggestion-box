package com.aldeadavila.suggestionbox.data.repository


import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.aldeadavila.suggestionbox.core.Config.SUGGESTIONS
import com.aldeadavila.suggestionbox.core.Config.USERS
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.google.android.play.integrity.internal.c
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.time.Instant
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

class SuggestionRepositoryImpl @Inject constructor(
    @Named(SUGGESTIONS) private val suggestionsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(SUGGESTIONS) private val storageSuggestionsRef: StorageReference
) : SuggestionRepository {

    override suspend fun createSuggestion(
        suggestion: Suggestion,
        files: List<File>
    ): Response<Boolean> {
        return try {

            val fromFile0 = Uri.fromFile(files[0])
            val ref0 = storageSuggestionsRef.child(files[0].name)
            ref0.putFile(fromFile0).await()
            val url0 = ref0.downloadUrl.await()
            suggestion.images.add(0, url0.toString())

            val fromFile1 = Uri.fromFile(files[1])
            val ref1 = storageSuggestionsRef.child(files[1].name)
            ref1.putFile(fromFile1).await()
            val url1 = ref1.downloadUrl.await()
            suggestion.images.add(1, url1.toString())

            suggestionsRef.add(suggestion).await()
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getSuggestions(): Flow<Response<List<Suggestion>>> = callbackFlow {
        val snapshopListener = suggestionsRef.addSnapshotListener { snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {
                val suggestionResponse = if(snapshot != null) {
                    val suggestions = snapshot.toObjects(Suggestion::class.java)
                    suggestions.map { suggestion ->
                        async {
                            suggestion.user = usersRef.document(suggestion.user_id).get().await().toObject(
                                User::class.java)!!
                        }
                    }.forEach {
                        it.await()
                    }
                    Response.Success(suggestions)
                } else {
                    Response.Failure(Exception(e?.message ?: "Error desconocido"))
                }
                trySend(suggestionResponse)
            }
        }
        awaitClose {
            snapshopListener.remove()
        }

    }

}