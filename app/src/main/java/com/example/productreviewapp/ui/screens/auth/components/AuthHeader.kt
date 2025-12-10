package com.example.productreviewapp.ui.screens.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthHeader(title1: String, title2: String?,modifier: Modifier){
    Column(
        modifier = modifier
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title1,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            color = Color.White
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = title2 ?:"",
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            color = Color.White
        )
    }
}