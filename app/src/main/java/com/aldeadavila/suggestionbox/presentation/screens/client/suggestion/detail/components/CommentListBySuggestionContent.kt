package com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Comment
import com.aldeadavila.suggestionbox.presentation.screens.client.suggestion.detail.SuggestionDetailViewModel
import com.aldeadavila.suggestionbox.presentation.util.Constants
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_primary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_secondary
import com.aldeadavila.suggestionbox.ui.theme.md_theme_light_tertiaryContainer
import com.aldeadavila.suggestionbox.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentListBySuggestionContent(
    navHostController: NavHostController,
    comments: List<Comment>,
    vm: SuggestionDetailViewModel = hiltViewModel()
) {

    val state = vm.state

    LazyColumn(
        modifier = Modifier
            .padding(start = 10.dp)
            .height(300.dp)
    ) {

        items(items = comments) {
            CommentListBySuggestionItem(navHostController = navHostController, comment = it)
            Spacer(modifier = Modifier.height(3.dp))
        }
        item {
            if (vm.currentUser?.isAnonymous == false) {
                TextField(
                    value = state.content,
                    onValueChange = { vm.onCommentContentInput(it) },
                    label = { Text("Añade tu comentario") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = md_theme_light_primary,
                        focusedLabelColor = md_theme_light_primary,
                        cursorColor = md_theme_light_primary,
                        containerColor = Color.White
                    ),
                )

                Spacer(modifier = Modifier.height(5.dp))

                Button(
                    onClick = {
                        vm.onNewComment()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(48.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        md_theme_light_primary,
                                        md_theme_light_secondary
                                    )
                                ),
                                shape = RoundedCornerShape(50.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Constants.CREATE_COMMENT,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = md_theme_light_tertiaryContainer,
                                fontFamily = poppins
                            ),
                        )
                    }
                }
            }

        }
    }

}