package com.example.productreviewapp.ui.screens.versusScreen.versusComponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.productreviewapp.ui.theme.BlueGradient
import com.example.productreviewapp.ui.theme.RedGradient

@Composable
fun VersusBar(value1: Number, value2: Number){
    val maxValue: Float = value1.toFloat() + value2.toFloat()
    val progress1 = (value1.toFloat() / maxValue).coerceIn(0f, 1f)
    val progress2 = (value2.toFloat() / maxValue).coerceIn(0f, 1f)
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(value1.toString())
                GradientProgressBar(
                    progress = progress1,
                    modifier = Modifier,
                    colorsGradient = BlueGradient,
                    isInverted = true
                )
                Spacer(Modifier.height(21.dp))
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(value2.toString())
                GradientProgressBar(
                    progress = progress2,
                    modifier = Modifier,
                    colorsGradient = RedGradient,
                    isInverted = false
                )
                Spacer(Modifier.height(21.dp))
            }
        }
        Spacer(
            Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
    }
}

@Composable
fun GradientProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    colorsGradient: List<Color>,
    isInverted: Boolean
) {
    Canvas(
        modifier
            .height(12.dp)
            .fillMaxWidth()
            .graphicsLayer { scaleX = if (isInverted) -1f else 1f}
            .clip(CircleShape)
    ) {

        val width = size.width * progress
        val height = size.height

        drawRect(
            color = Color.LightGray.copy(alpha = 0.4f),
            size = size
        )

        drawRect(
            brush = Brush.horizontalGradient(colorsGradient),
            size = Size(width, height)
        )
    }
}