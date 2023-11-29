package com.aldeadavila.suggestionbox.data.datasource.remote

import com.aldeadavila.suggestionbox.domain.model.Suggestion
import retrofit2.Response
import java.io.File

interface SuggestionsRemoteDataSource {

    suspend fun findAll(): Response<List<Suggestion>>
    suspend fun findByCategory(idCategory: String): Response<List<Suggestion>>
    suspend fun create(suggestion: Suggestion, files: List<File>): Response<Suggestion>
    suspend fun updateWithImage(id: String, suggestion: Suggestion, files: List<File>?): Response<Suggestion>
    suspend fun update(id: String, suggestion: Suggestion): Response<Suggestion>
    suspend fun detele(id: String): Response<Unit>
}