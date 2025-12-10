package com.example.productreviewapp.ui.screens.accountScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.viewmodels.UserViewModel

@Composable
fun EditAccountScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val vm: UserViewModel = viewModel()

    LaunchedEffect(Unit) {}

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Editar Cuenta",
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        fun Modifier.inputModifier() = this
            .fillMaxWidth()
            .padding(vertical = 6.dp)

        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.name = it },
            label = { Text("Nombre de Usuario") },
            modifier = Modifier.inputModifier(),
            shape = RoundedCornerShape(16.dp)
        )

        OutlinedTextField(
            value = vm.email,
            onValueChange = { vm.email = it },
            label = { Text("Correo") },
            modifier = Modifier.inputModifier(),
            shape = RoundedCornerShape(16.dp)
        )

        OutlinedTextField(
            value = vm.password,
            onValueChange = { vm.password = it },
            label = { Text("Nueva Contraseña") },
            modifier = Modifier.inputModifier(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                vm.updateUser()
                navController.popBackStack()
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Cambios", color = Color.White)
        }
    }
}
