package com.example.productreviewapp.ui.screens.accountScreen.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AccountButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit, color: Color){
    Button(
        onClick = { onClick()},
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = modifier
            .padding(vertical = 10.dp)
    ) {
        Text(text)
    }
}