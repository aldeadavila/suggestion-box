package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.presentation.components.DefaultButton
import com.aldeadavila.suggestionbox.presentation.components.DotsIndicator
import com.aldeadavila.suggestionbox.presentation.components.SliderView
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.ClientSuggestionDetailViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ClientSuggestionDetailContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    vm: ClientSuggestionDetailViewModel = hiltViewModel()
) {

    val state = rememberPagerState()

    Box(
        modifier = Modifier.padding(paddingValues)
    ) {
        Column (
            modifier = Modifier
                .padding(20.dp)
             // .verticalScroll(rememberScrollState())
        ) {
            SliderView(
                state = state,
                images = vm.listProductImage
            )
            Spacer(modifier = Modifier.height(4.dp))
            DotsIndicator(
                totalDots = vm.listProductImage.size,
                selectedIndex = state.currentPage
            )
            Text(
                text = vm.suggestion.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Divider(
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color.Gray
            )
            Text(
                modifier = Modifier.padding(bottom = 7.dp),
                text = "Descripción",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = vm.suggestion.description,
                fontSize = 15.sp
            )

            GetCommentsBySuggestion(
                navHostController = navHostController,
                paddingValues = paddingValues
            )
        }

    }

    LaunchedEffect(key1 = state.currentPage) {
        delay(3000)
        var newPosition = state.currentPage + 1
        if (newPosition > vm.listProductImage.size - 1) {
            newPosition = 0
        }
        state.animateScrollToPage(newPosition)
    }
}