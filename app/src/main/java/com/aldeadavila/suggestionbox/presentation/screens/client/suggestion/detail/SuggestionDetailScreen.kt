package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components.SuggestionDetailContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SuggestionDetailScreen(
    navHostController: NavHostController, 
    suggestionParam: String,
    vm: SuggestionDetailViewModel = hiltViewModel()
) {
    vm.savedStateHandle["suggestion"] = suggestionParam
    
    Scaffold {
        SuggestionDetailContent(
            navHostController = navHostController,
            paddingValues = it
        )
    }
}