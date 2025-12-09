package com.example.productreviewapp.ui.screens.homeScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productreviewapp.domain.utils.SharedPref
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute

@Composable
fun ButtonLogout(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Button(
            onClick = {
                SharedPref.clear()
                navController.navigate(LoginScreenRoute) {
                    popUpTo(HomeScreenRoute) { inclusive = true }
                }
            },
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Cerrar Sesión")
        }
    }
}