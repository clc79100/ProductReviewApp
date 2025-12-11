package com.example.productreviewapp.ui.screens.reviewScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.productreviewapp.ui.theme.commentBackground
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel

@Composable
fun BottomComments(
    vm: ReviewViewModel
){
    Box(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            /*.clickable {
                vm.showSheet = true
            }*/
            .padding(all = 16.dp)
    ) {
        val recentComment =
            vm.review?.comments?.maxByOrNull { it.createdAt }

        Text(
            text = "Comentarios",
            fontWeight = FontWeight.SemiBold
        )
        val text = recentComment?.let {
            "${it.user?.name}: ${it.content}"
        } ?: "No hay comentarios"

        Text(
            text = text,
            color = Color.Gray,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = commentBackground,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    vm.showSheet = true
                }
                .padding(12.dp)
        )

    }
}