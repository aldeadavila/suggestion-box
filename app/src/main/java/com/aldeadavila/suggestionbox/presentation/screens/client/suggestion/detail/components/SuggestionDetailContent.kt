package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.PaddingValues
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.components.DotsIndicator
import com.aldeadavila.suggestionbox.presentation.components.SliderView
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SuggestionDetailContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    vm: SuggestionDetailViewModel = hiltViewModel()
) {
    val pageState = rememberPagerState()
    var key by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .clickable { keyboard?.hide() }
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

        vm.suggestion?.let { suggestion ->
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                SliderView(
                    state = pageState,
                    images = vm.listSuggestionImage
                )
                Spacer(modifier = Modifier.height(4.dp))
                DotsIndicator(
                    totalDots = vm.listSuggestionImage.size,
                    selectedIndex = pageState.currentPage
                )
                Text(
                    text = suggestion.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Color.Gray
                )

                Text(
                    text = suggestion.description,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    textAlign = TextAlign.Right,
                    text = "de " + suggestion.user?.nickname,
                    color = Color.Gray,
                    fontSize = 8.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                GetCommentsBySuggestion(
                    navHostController = navHostController
                )
            }
        }
    }

    LaunchedEffect(key1 = key, key2 = vm.errorMessage) {
        launch {
            if (vm.errorMessage != "") {
                Toast.makeText(
                    context,
                    vm.errorMessage,
                    Toast.LENGTH_LONG
                ).show()
                vm.errorMessage = ""
            }
            delay(8000)
            with(pageState) {
                val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
                animateScrollToPage(page = target)
                key = !key
            }
        }
    }
}