package com.aldeadavila.suggestionbox.presentation.screens.roles.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.screens.roles.RolesViewModel

@Composable
fun RolesContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    vm: RolesViewModel = hiltViewModel()
) {

    val data = vm.authResponse.user

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = data?.roles ?: arrayListOf()) { rol ->
            RolesItem(
                rol = rol,
                navHostController
            )
        }
    }
}