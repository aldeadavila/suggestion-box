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
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Suggestion
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCategoryScreen
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientSuggestionScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.list.ClientSuggestionListViewModel

@Composable
fun ClientProductListItem(
    navHostController: NavHostController,
    suggestion: Suggestion,
    user: User?,
    vm: ClientSuggestionListViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 15.dp
            )
            .height(200.dp)
            .clickable {
                navHostController.navigate(
                    route = ClientSuggestionScreen.SuggestionDetail.passSuggestion(
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
                    text = suggestion.name,
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                        textAlign = TextAlign.Right,
                    text = "by " + user!!.name,
                    color = Color.Gray,
                    fontSize = 8.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = suggestion.description,
                    color = Color.Gray,
                    fontSize = 12.sp
                )

            }
            Spacer(modifier = Modifier.width(10.dp))

             AsyncImage(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = suggestion.image1,
                contentDescription = ""
            )

            if (vm.getEditable(suggestion.idUser)) {
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                navHostController.navigate(
                                    route = ClientCategoryScreen.SuggestionUpdate.passSuggestion(
                                        suggestion.toJson()
                                    )
                                )
                            },
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "editar")
                    /*Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                // vm.deletesuggestion(suggestion.id ?: "")
                            },
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = "eliminar"
                    )*/
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier.padding(end = 80.dp),
            color = Color.LightGray
        )
    }

}
