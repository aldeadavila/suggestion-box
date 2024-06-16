package com.aldeadavila.suggestionbox.data.repository


import android.net.Uri
import com.aldeadavila.suggestionbox.core.Config.COMMENTS
import com.aldeadavila.suggestionbox.core.Config.SUGGESTIONS
import com.aldeadavila.suggestionbox.core.Config.USERS
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
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
import javax.inject.Inject
import javax.inject.Named

class SuggestionRepositoryImpl @Inject constructor(
    @Named(SUGGESTIONS) private val suggestionsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(SUGGESTIONS) private val storageSuggestionsRef: StorageReference
) : SuggestionRepository {

    override suspend fun updateSuggestion(
        suggestion: Suggestion,
        files: List<File>
    ): Response<Boolean> {
        return try {
            if (files[0] != null) {
                val fromFile0 = Uri.fromFile(files[0])
                val ref0 = storageSuggestionsRef.child(files[0].name)
                ref0.putFile(fromFile0).await()
                val url0 = ref0.downloadUrl.await()
                suggestion.images.add(0, url0.toString())
            }

            if (files[1] != null) {
                val fromFile1 = Uri.fromFile(files[1])
                val ref1 = storageSuggestionsRef.child(files[1].name)
                ref1.putFile(fromFile1).await()
                val url1 = ref1.downloadUrl.await()
                suggestion.images.add(1, url1.toString())
            }

            val map: MutableMap<String, Any> = HashMap();
            map["title"] = suggestion.title
            map["description"] = suggestion.description
            map["category"] = suggestion.category
            if (files[0] != null || files[1] != null) {
                map["images"] = suggestion.images
            }
            map["title"] = suggestion.title

            suggestionsRef.document(suggestion.suggestion_id).update(map).await()
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }
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

            //Generamos el id
            val document = suggestionsRef.document()

            suggestion.suggestion_id = document.id
            suggestionsRef.document(document.id).set(suggestion).await()
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getSuggestions(): Flow<Response<List<Suggestion>>> = callbackFlow {

        val snapshopListener = suggestionsRef.addSnapshotListener { snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {
                val suggestionResponse = if (snapshot != null) {
                    val suggestions = snapshot.toObjects(Suggestion::class.java)
                    val idUserArray = ArrayList<String>()
                    suggestions.forEach { suggestion ->
                        idUserArray.add(suggestion.user_id)
                    }

                    val idUserList = idUserArray.toSet().toList()
                    idUserList.map { id ->
                        async {
                            val user = usersRef.document(id).get().await().toObject(
                                User::class.java
                            )!!
                            suggestions.forEach { suggestion ->
                                if (suggestion.user_id == id) {
                                    suggestion.user = user
                                }
                            }
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