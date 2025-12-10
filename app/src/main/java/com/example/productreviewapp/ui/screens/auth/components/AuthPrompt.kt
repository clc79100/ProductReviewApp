package com.example.productreviewapp.ui.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productreviewapp.ui.screens.RegisterScreenRoute
import com.example.productreviewapp.ui.theme.BlueGradient

@Composable
fun AuthPrompt(navController: NavController, onClick: () -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "¿No tienes cuentas?",
            color = Color.Gray
        )
        Text(
            text = "Registrate",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = BlueGradient[0],
            modifier = Modifier.clickable{
                onClick()
            }
        )
    }
}