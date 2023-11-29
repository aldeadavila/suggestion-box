package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SuggestionRepository {

    fun findAll(): Flow<Resource<List<Suggestion>>>
    fun findByCategory(idCategory: String): Flow<Resource<List<Suggestion>>>
    suspend fun create(suggestion: Suggestion, files: List<File>): Resource<Suggestion>
    suspend fun updateWithImage(id:String, suggestion: Suggestion, files: List<File>?): Resource<Suggestion>
    suspend fun update(id:String, suggestion: Suggestion): Resource<Suggestion>
    suspend fun detele(id: String): Resource<Unit>
}