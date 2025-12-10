package com.example.productreviewapp.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.auth.components.AuthHeader
import com.example.productreviewapp.ui.theme.BlueGradient
import com.example.productreviewapp.ui.viewmodels.AuthViewModel

@Composable
fun RegisterScreen(
    paddingValues: PaddingValues,
    navController: NavController
){
    val vm : AuthViewModel = viewModel()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(BlueGradient.reversed()))
            .padding(top = 20.dp),
    ) {

        AuthHeader(
            title1 = "¡Crea tu cuenta!",
            title2 = "Regístrate",
            modifier = Modifier.weight(0.5f)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .shadow(16.dp, shape = RoundedCornerShape(topEnd = 80.dp))
                .clip(RoundedCornerShape(topEnd = 80.dp))
                .background(Color.White)
                .padding(top = 20.dp, bottom = 30.dp, start = 30.dp, end = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 40.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedTextField(
                    value = vm.name,
                    onValueChange = { vm.name = it },
                    label = { Text("Nombre") },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = vm.email,
                    onValueChange = { vm.email = it },
                    label = { Text("Correo") },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = vm.password,
                    onValueChange = { vm.password = it },
                    label = { Text("Contraseña") },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                OutlinedTextField(
                    value = vm.confirmPassword,
                    onValueChange = { vm.confirmPassword = it },
                    label = { Text("Confirmar Contraseña") },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            // BOTÓN (igual que Login)
            Button(
                enabled = vm.isEnabled,
                onClick = {
                    vm.register()
                    focusManager.clearFocus()
                    vm.showAlertDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0059FF)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Registrarse",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }

    if (vm.loading){
        CustomLoading()
    }

    if (vm.showAlertDialog){
        AlertDialog(
            onDismissRequest = {},
            title = { Text("ya te registraste xd") },
            text = { Text("inicia sesion pibe 😹") },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        vm.showAlertDialog = false
                        navController.popBackStack()
                    }
                ) {
                    Text("Regresar a Iniciar Sesion")
                }
            },
            dismissButton = {}
        )
    }
}