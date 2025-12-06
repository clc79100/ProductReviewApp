package com.example.productreviewapp.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
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
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.name = it },
            label = {Text("Nombre")},
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = vm.email,
            onValueChange = { vm.email = it },
            label = {Text("Correo")},
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = vm.password,
            onValueChange = {vm.password = it},
            label = {Text("Contraseña")},
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = vm.confirmPassword,
            onValueChange = {vm.confirmPassword = it},
            label = {Text("Confirmar Contraseña")},
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = vm.profilePhoto,
            onValueChange = {vm.profilePhoto = it},
            label = {Text("Foto de Perfil")},
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            enabled = vm.isEnabled,
            onClick = {
                vm.register()
                focusManager.clearFocus()
                vm.showAlertDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Registrarse",
                fontSize = 16.sp
            )
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