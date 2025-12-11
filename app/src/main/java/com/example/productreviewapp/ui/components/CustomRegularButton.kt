package com.example.productreviewapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.productreviewapp.ui.theme.MainPurple

@Composable
fun CustomRegularButon(text: String, enabled: Boolean = true, onClick: () -> Unit, ){
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = MainPurple,
            contentColor = Color.White
        ),
        enabled = enabled,
        onClick = {
            onClick()
        }
    ) {
        Text(text)
    }
}