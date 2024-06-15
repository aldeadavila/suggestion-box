package com.aldeadavila.suggestionbox.data.repository


import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.aldeadavila.suggestionbox.core.Config.SUGGESTIONS
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.google.android.play.integrity.internal.c
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.File
import java.time.Instant
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

class SuggestionRepositoryImpl @Inject constructor(
    @Named(SUGGESTIONS) private val suggestionsRef: CollectionReference,
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



            //Generamos el id
            val document = suggestionsRef.document()

            suggestion.id = document.id
            suggestionsRef.document(document.id).set(suggestion).await()
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }

}