package com.example.productreviewapp.ui.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productreviewapp.ui.theme.BlueGradient

@Composable
fun CustomButton(modifier: Modifier, onClick: () -> Unit){
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(CircleShape)
                .clickable{
                    onClick()
                }
                .background(Brush.linearGradient(
                    colors = listOf(Color.White, BlueGradient[0]),
                    start = Offset(0f, 2000f),  // empuja el blanco hacia abajo
                    end = Offset(800f, 0f)
                )),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Iniciar Sesión",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)
        }
    }
}