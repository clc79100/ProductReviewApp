package com.example.productreviewapp.ui.screens.accountScreen.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.productreviewapp.ui.theme.MainPurple

@Composable
fun PhotoProfile(profilePhoto: String?, onClick: () -> Unit){
    Box(
        modifier = Modifier
            .size(160.dp)
            .border(
                color = MainPurple,
                width = 3.dp,
                shape = CircleShape
            )
            .padding(6.dp),
        contentAlignment = Alignment.BottomEnd
    ) {

        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.fillMaxSize()
        )

        if (!profilePhoto.isNullOrEmpty())
            AsyncImage(
                model = profilePhoto,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onClick() }
                .background(MainPurple)
                .padding(10.dp)
        )
    }
}