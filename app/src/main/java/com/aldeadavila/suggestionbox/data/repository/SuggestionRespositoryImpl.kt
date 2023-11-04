package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton


class SuggestionRespositoryImpl @Inject constructor(
    private val suggestionList: CollectionReference
) :SuggestionRepository {

    override fun addNewSuggestion(suggestion: Suggestion) {
        try {
            suggestionList.document(suggestion.id).set(suggestion)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}