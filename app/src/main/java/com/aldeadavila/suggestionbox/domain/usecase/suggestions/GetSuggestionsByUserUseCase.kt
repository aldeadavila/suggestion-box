package com.aldeadavila.suggestionbox.domain.usecase.suggestions

import com.aldeadavila.suggestionbox.domain.repository.SuggestionRepository
import javax.inject.Inject

class GetSuggestionsByUserUseCase @Inject constructor(private val repository: SuggestionRepository) {

    operator fun invoke(user_id: String) = repository.getSuggestionsByUser(user_id)
}