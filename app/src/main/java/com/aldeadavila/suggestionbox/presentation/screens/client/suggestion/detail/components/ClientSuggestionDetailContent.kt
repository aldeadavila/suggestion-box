package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.components.DotsIndicator
import com.aldeadavila.suggestionbox.presentation.components.SliderView
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.ClientSuggestionDetailViewModel
import com.aldeadavila.suggestionbox.presentation.util.Constants
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins
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

    val pageState = rememberPagerState()


    Box(
        modifier = Modifier.padding(paddingValues)
    ) {
        Image(
            modifier = Modifier
                .padding(start = 50.dp)
                .size(400.dp),
            painter = painterResource(id = R.drawable.bg_green),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopEnd
        )
        Column (
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
             // .verticalScroll(rememberScrollState())
        ) {
            SliderView(
                state = pageState,
                images = vm.listProductImage
            )
            Spacer(modifier = Modifier.height(4.dp))
            DotsIndicator(
                totalDots = vm.listProductImage.size,
                selectedIndex = pageState.currentPage
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

            Spacer(modifier = Modifier.weight(1f))


            GetCommentsBySuggestion(
                navHostController = navHostController,
                paddingValues = paddingValues
            )

        }

    }

    LaunchedEffect(key1 = pageState.currentPage) {
        delay(20000)
        var newPosition = pageState.currentPage + 1
        if (newPosition > vm.listProductImage.size - 1) {
            newPosition = 0
        }
        pageState.animateScrollToPage(newPosition, 0.1f)
    }
}