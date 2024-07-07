package com.aldeadavila.suggestionbox.domain.repository

import com.aldeadavila.suggestionbox.domain.model.News
import com.aldeadavila.suggestionbox.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<Response<List<News>>>
}