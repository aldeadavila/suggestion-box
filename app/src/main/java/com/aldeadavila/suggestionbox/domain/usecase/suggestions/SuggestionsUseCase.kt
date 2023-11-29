package com.aldeadavila.suggestionbox.domain.usecase.suggestions

data class SuggestionsUseCase(
    val createSuggestionUseCase: CreateSuggestionUseCase,
    val findByCategory: FindByCategoryUseCase,
    val findAll : FindAllUseCase,
    val updateSuggestion: UpdateSuggestionUseCase,
    val updateSuggestionWithImage: UpdateSuggestionWithImageUseCase,
    val deleteSuggestion: DeleteSuggestionUseCase,

    )
