package com.example.productreviewapp.ui.screens.versusScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.productreviewapp.domain.models.Product

@Composable
fun SelectProductCard(product: Product,onClick: () -> Unit){
    Column(
        modifier = Modifier
            .clickable{onClick()},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(product.name)
    }
}