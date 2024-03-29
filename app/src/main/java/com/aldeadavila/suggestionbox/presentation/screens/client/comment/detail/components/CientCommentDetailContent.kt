package com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.navigation.screen.client.ClientCommentScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.comment.detail.ClientCommentDetailViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ClientCommentDetailContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    vm: ClientCommentDetailViewModel = hiltViewModel()
) {

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
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .size(350.dp)
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = vm.comment.content,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 7.dp)
                        .align(alignment = Alignment.End),
                    text = "Creado por " + vm.user?.nickname,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )

            }
            if (vm.getEditable(vm.comment.idUser)) {
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                navHostController.navigate(
                                    route = ClientCommentScreen.CommentUpdate.passComment(
                                        vm.comment.toJson()
                                    )
                                )
                            },
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "editar"
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                vm.deleteComment(vm.comment.id ?: "")
                            },
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = "eliminar"
                    )
                }
            }
        }
    }
}