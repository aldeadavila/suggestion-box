package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.ClientSuggestionDetailViewModel
import com.aldeadavila.suggestionbox.ui.theme.md_theme_dark_onTertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primaryContainer

@Composable
fun ClientCommentListBySuggestionItem(
    navHostController: NavHostController,
    comment: Comment,
    vm: ClientSuggestionDetailViewModel = hiltViewModel()
) {

    var mDisplayMenu by remember { mutableStateOf(false) }
    val isFromMe = vm.isFromMe(comment.idUser)
    val mContext = LocalContext.current

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
                .background(md_theme_dark_onTertiaryContainer)
                .padding(5.dp),
            contentAlignment = Alignment.TopEnd
        ) {

                Text(
                    modifier = Modifier.padding(end = 15.dp),
                    text = comment.content,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Left
                )

                IconButton(
                     onClick = { mDisplayMenu = !mDisplayMenu },
                     modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(15.dp),
                )
                    {
                    Icon(

                        imageVector = Icons.Default.MoreVert,
                        contentDescription = ""
                    )
                }

                DropdownMenu(
                    expanded = mDisplayMenu,
                    onDismissRequest = { mDisplayMenu = false }
                ) {

                    DropdownMenuItem(
                        text = { Text("Editar mensaje") },
                        onClick = { vm.updateComment(comment) }
                    )
                    DropdownMenuItem(
                        text = { Text("Eliminar mensaje") },
                        onClick = {
                            vm.deleteComment(comment.id!!)
                        }
                    )
                }

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