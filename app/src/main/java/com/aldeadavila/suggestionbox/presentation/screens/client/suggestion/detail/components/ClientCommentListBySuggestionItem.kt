package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.ClientSuggestionDetailViewModel
import com.aldeadavila.suggestionbox.ui.theme.PurpleGrey80

@Composable
fun ClientCommentListBySuggestionItem(
    navHostController: NavHostController,
    comment: Comment,
    vm: ClientSuggestionDetailViewModel = hiltViewModel()
) {

    val isFromMe =  vm.isFromMe(comment.idUser)

    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 48f,
                    topEnd = 48f,
                    bottomStart = if (isFromMe) 48f else 0f,
                    bottomEnd = if (isFromMe) 0f else 48f
                )
            )
            .background(if (isFromMe) PurpleGrey80 else Color.Green)
            .padding(5.dp),
        contentAlignment = if(isFromMe) Alignment.TopEnd else Alignment.TopStart
    ) {
        Text(
            text = comment.content,
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
    }

}