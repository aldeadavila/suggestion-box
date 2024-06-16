package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.navigation.ClientCommentScreen
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailViewModel
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primaryContainer

@Composable
fun CommentListBySuggestionItem(
    navHostController: NavHostController,
    comment: Comment,
    vm: SuggestionDetailViewModel = hiltViewModel()
) {

    val isFromMe = vm.isFromMe(comment.user_id)

    if (isFromMe) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = 0f,
                        bottomEnd = 48f
                    )
                )
                .background(Color.White)
                .padding(5.dp)
                .clickable {
                    navHostController.navigate(
                        route = ClientCommentScreen.CommentDetail.passComment(
                            comment.toJson()
                        )
                    )
                },
            contentAlignment = Alignment.TopEnd
        ) {

            Text(
                modifier = Modifier.padding(end = 25.dp),
                text = comment.content,
                fontSize = 10.sp,
                textAlign = TextAlign.Left
            )

            Image(
                modifier = Modifier
                    .size(15.dp)
                    .clickable {
                        navHostController.navigate(
                            route = ClientCommentScreen.CommentDetail.passComment(
                                comment.toJson()
                            )
                        )
                    },
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "editar"
            )

        }
    } else {

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = 48f,
                        bottomEnd = 0f
                    )
                )
                .background(md_theme_light_primaryContainer)
                .padding(5.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = comment.content,
                fontSize = 10.sp
            )

        }

    }

    Spacer(modifier = Modifier.height(4.dp))
}