package com.aldeadavila.suggestionbox.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.aldeadavila.suggestionbox.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState


@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun SliderView(state: PagerState, images: List<String>) {

    val imageUrl = remember { mutableStateOf("") }
    val horizontalPadding = 16
    val itemWidth = LocalConfiguration.current.screenWidthDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val contentPadding = PaddingValues(start = (horizontalPadding).dp, end = (screenWidth - itemWidth + horizontalPadding).dp)
    HorizontalPager(
        contentPadding = PaddingValues(start = (horizontalPadding).dp, end = (screenWidth - itemWidth + horizontalPadding).dp),
        state = state,
        count = images.size,
        modifier = Modifier
            .height(150.dp)
            .widthIn(itemWidth.dp)
            .fillMaxSize()
            .background(color = Color.White),
        itemSpacing = 20.dp,

    ) { page ->
        imageUrl.value = images[page]

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = imageUrl.value)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.user_image)
                            scale(Scale.FILL)
                        }).build()
                )
                ZoomableImage(image = imageUrl.value)
                /*GlideImage(
                    modifier = Modifier
                        .clickable(onClick = {})
                        .clip(RoundedCornerShape(20.dp)),
                    model= imageUrl.value,
                    alignment = Alignment.Center,
                    contentDescription = "",
                )*/
            }
        }
    }
}

