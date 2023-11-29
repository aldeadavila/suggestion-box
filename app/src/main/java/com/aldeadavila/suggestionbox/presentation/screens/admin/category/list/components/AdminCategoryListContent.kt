package com.aldeadavila.suggestionbox.presentation.screens.admin.category.list.components


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Category


@Composable
fun AdminScreenCategoryListContent(navHostController: NavHostController, categories: List<Category>, paddingValues: PaddingValues) {

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(
            items = categories
        ) {
            AdminCategoryListItem(navHostController, category = it)
        }
    }
}