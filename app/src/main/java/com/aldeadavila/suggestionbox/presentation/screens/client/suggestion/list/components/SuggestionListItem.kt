package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.presentation.navigation.DetailsScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.SuggestionListViewModel

@Composable
fun SuggestionListItem(
    navHostController: NavHostController,
    suggestion: Suggestion,
    vm: SuggestionListViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 10.dp,
                top = 5.dp
            )
            .height(110.dp)
            .clickable {
                navHostController.navigate(
                    route = DetailsScreen.DetailSuggestion.passSuggestion(
                        suggestion.toJson()
                    )
                )
            }

    ) {
        Row {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = vm.printTitle(suggestion.title),
                    color = Color.Black,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = vm.printDescription(suggestion.description),
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Autor: " + suggestion.user?.nickname,
                    color = Color.Black,
                    fontSize = 8.sp
                )


            }
            Spacer(modifier = Modifier.width(5.dp))


            if (vm.getEditable(suggestion.user_id)) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        model = suggestion.images.get(0),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column {
                        Image(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    navHostController.navigate(
                                        route = DetailsScreen.UpdateSuggestion.passSuggestion(
                                            suggestion.toJson()
                                        )
                                    )
                                },
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "editar"
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Image(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    // vm.deleteSuggestion(suggestion.id ?: "")
                                },
                            painter = painterResource(id = R.drawable.trash),
                            contentDescription = "eliminar"
                        )
                    }
                }
            } else {
                AsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    model = suggestion.images[0],
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider(
            modifier = Modifier.padding(end = 80.dp),
            color = Color.LightGray
        )
    }

}
