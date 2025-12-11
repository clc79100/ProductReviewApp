package com.example.productreviewapp.ui.screens.versusScreen.selectProductsComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.productreviewapp.domain.models.Product

@Composable
fun ProductCard(
    product: Product?,
    gradientColors: List<Color>,
    modifier: Modifier,
    onClick: () -> Unit,
    ){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .background(Color.LightGray.copy(0.2f)),
    ) {
        Column{
            Spacer(Modifier.weight(10f))
            Spacer(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(gradientColors)
                    )
                    .weight(0.2f)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .clickable{onClick()}
                        .border(2.dp, Color.Red, CircleShape)
                        .padding(2.dp)
                )
            }

            AsyncImage(
                model = product?.image,
                contentDescription = product?.name,
                modifier = Modifier
                    .weight(1f)
            )
            Text(product?.name ?: "")
            Text("$${product?.price}")
        }
    }
}