package com.aldeadavila.suggestionbox.domain.usecase.suggestions

data class SuggestionsUseCases(
    val createSuggestionUseCase: CreateSuggestionUseCase,
    val findByCategory: FindByCategoryUseCase,
    val getSuggestionsUseCase: getSuggestionsUseCase,
    val updateSuggestion: UpdateSuggestionUseCase
)
