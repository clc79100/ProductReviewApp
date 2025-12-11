package com.example.productreviewapp.ui.screens.reviewScreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ReviewContent(
    vm: ReviewViewModel
){
        AsyncImage(
            model = vm.review?.product?.image ?: "",
            contentDescription = vm.review?.product?.name ?: "Sin titulo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(500.dp)
                .padding(10.dp)
        )
        Text(
            text = "Precio: $${vm.review?.product?.price}",
            fontSize = 15.sp,
            color = Color.Gray
        )
        MarkdownText(
            vm.review?.content ?: "Sin contenido",
            modifier = Modifier
                .padding(bottom = 50.dp)
        )
}