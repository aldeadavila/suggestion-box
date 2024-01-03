package com.aldeadavila.suggestionbox.presentation.navigation.screen.admin

sealed class AdminCategoryScreen(val route: String) {
    object CategoryCreate: AdminCategoryScreen("admin/category/create")
    object CategoryUpdate: AdminCategoryScreen("admin/category/update/{category}") {
        fun passCategory(category: String) = "admin/category/update/$category"
    }
    object SuggestionList: AdminCategoryScreen("admin/category/suggestions/list/{category}") {
        fun passCategory(category: String) = "admin/category/suggestions/list/$category"
    }

    object SuggestionCreate: AdminCategoryScreen("admin/category/suggestions/create/{category}") {
        fun passCategory(category: String) = "admin/category/suggestions/create/$category"
    }

    object SuggestionUpdate: AdminCategoryScreen("admin/category/suggestions/update/{suggestion}") {
        fun passSuggestion(suggestion: String) = "admin/category/suggestions/update/$suggestion"
    }

}
