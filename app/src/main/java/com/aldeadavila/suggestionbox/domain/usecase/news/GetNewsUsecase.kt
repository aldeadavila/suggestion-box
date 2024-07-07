package com.aldeadavila.suggestionbox.domain.usecase.news

import com.aldeadavila.suggestionbox.domain.repository.LocationsRepository
import com.aldeadavila.suggestionbox.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUsecase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke() = repository.getNews()
}