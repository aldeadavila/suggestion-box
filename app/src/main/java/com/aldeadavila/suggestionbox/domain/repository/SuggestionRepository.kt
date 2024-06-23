package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SuggestionRepository {
    suspend fun createSuggestion(suggestion: Suggestion, files: List<File>): Response<Boolean>

    fun getSuggestions(): Flow<Response<List<Suggestion>>>

    suspend fun updateSuggestion(suggestion: Suggestion, files: List<File>): Response<Boolean>

    suspend fun delete(idPost: String): Response<Boolean>
}