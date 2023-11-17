package com.aldeadavila.suggestionbox.presentation.screens.client.product.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.presentation.components.DefaultButton
import com.aldeadavila.suggestionbox.presentation.components.DotsIndicator
import com.aldeadavila.suggestionbox.presentation.components.SliderView
import com.aldeadavila.suggestionbox.presentation.screens.client.product.detail.ClientProductDetailViewModel
import com.aldeadavila.suggestionbox.ui.theme.Gray100
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ClientProductDetailContent(
    paddingValues: PaddingValues,
    vm: ClientProductDetailViewModel = hiltViewModel()
) {

    val state = rememberPagerState()

    Box(
        modifier = Modifier.padding(paddingValues)
    ) {
        Column {
            SliderView(
                state = state,
                images = vm.listProductImage
            )
            Spacer(modifier = Modifier.height(4.dp))
            DotsIndicator(
                totalDots = vm.listProductImage.size,
                selectedIndex = state.currentPage
            )
        }

        Card(
            modifier = Modifier.padding(top = 310.dp),
            shape = RoundedCornerShape(
                topEnd = 40.dp,
                topStart = 40.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = vm.product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Gray100
                )
                Text(
                    modifier = Modifier.padding(bottom = 7.dp),
                    text = "Descripción",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = vm.product.description,
                    fontSize = 15.sp
                )
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Gray100
                )
                Text(
                    modifier = Modifier.padding(bottom = 7.dp),
                    text = "Precio",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = vm.product.price.toString(),
                    fontSize = 15.sp
                )
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Gray100
                )
                Text(
                    modifier = Modifier.padding(bottom = 7.dp),
                    text = "Tu orden",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Cantidad: ",
                    fontSize = 15.sp
                )
                Text(
                    text = "Precio C/U: 0",
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .width(100.dp)
                            .height(35.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "-",
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Text(
                                text = "0",
                                fontSize = 19.sp,
                                color = Color.White
                            )
                            Text(
                                text = "+",
                                fontSize = 19.sp,
                                color = Color.White
                            )
                        }
                    }
                }

                DefaultButton(
                    modifier = Modifier.width(200.dp),
                    text = "AGREGAR",
                    onClick = { })

            }
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