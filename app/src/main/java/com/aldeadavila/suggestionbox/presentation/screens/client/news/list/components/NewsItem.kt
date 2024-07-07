package com.aldeadavila.suggestionbox.presentation.screens.client.news.list.components

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.News
import com.aldeadavila.suggestionbox.presentation.navigation.DetailsScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.news.list.NewsViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NewsItem(
    navHostController: NavHostController,
    news: News,
    vm: NewsViewModel = hiltViewModel()
) {

    val dateFormated = SimpleDateFormat("dd/MM/yyyy").format(news.created_at.toDate())

    Column(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 10.dp,
                top = 5.dp
            )
            .clickable {
               navHostController.navigate(
                    route = DetailsScreen.DetailNews.passNews(
                        news.toJson()
                    )
                )
            }

    ) {
        Row {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = vm.printTitle(news.title),
                    color = Color.Black,
                    fontSize = 16.sp,
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = dateFormated,
                    color = Color.Gray,
                    fontSize = 12.sp
                )

            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(end = 20.dp),
            color = Color.LightGray
        )
    }
}