package com.example.productreviewapp.ui.screens.homeScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.productreviewapp.domain.models.Reviewer
import com.example.productreviewapp.ui.theme.MainPurple

@Composable
fun Reviewer(
    reviewer: Reviewer
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(
                    color = MainPurple,
                    width = 2.dp,
                    shape = CircleShape
                )
                .padding(4.dp),
            contentAlignment = Alignment.BottomEnd
        ) {

            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.fillMaxSize()
            )

            if (reviewer.profile_photo.isNotEmpty())
                AsyncImage(
                    model = reviewer.profile_photo,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
        }
        Text(
            text = reviewer.name,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}