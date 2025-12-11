package com.example.productreviewapp.ui.screens.reviewScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.productreviewapp.ui.theme.MainPurple
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel

@Composable
fun CommentOption(
    vm: ReviewViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable {
                vm.dialogContentComposable = {
                    CreateComment(
                        createComment = { comment ->
                            vm.createComment(comment)
                        }
                    )
                }
                vm.showDialog = true
            }
            .background(Color.White)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ChatBubbleOutline,
            contentDescription = "Comentar",
            tint = MainPurple,
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(
            text = "Nuevo Comentario...",
            color = Color.Gray
        )
    }
}