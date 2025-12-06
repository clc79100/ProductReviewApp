package com.example.productreviewapp.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute
import com.example.productreviewapp.ui.screens.RegisterScreenRoute
import com.example.productreviewapp.ui.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    navController: NavController
){
    val vm : AuthViewModel = viewModel()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(vm.isLogged) {
        if (vm.isLogged){
            navController.navigate(HomeScreenRoute){
                popUpTo(LoginScreenRoute){inclusive = true}
            }
        }
    }

    if (!vm.isLogged){
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
            Button(
                enabled = vm.isEnabled,
                onClick = {
                    vm.login()
                    focusManager.clearFocus() //esto quita el tecladoxd
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Iniciar Sesión",
                    fontSize = 16.sp
                )
            }
            Text(
                text = "Registrate",
                modifier = Modifier.clickable{
                    navController.navigate(RegisterScreenRoute)
                }
            )
        }
    }

    if (vm.loading){
        CustomLoading()
    }

    if (vm.showAlertDialog){
        AlertDialog(
            onDismissRequest = {vm.showAlertDialog = false},
            title = { Text("Error al Iniciar Sesion") },
            text = { Text("Correo o contraseña incorrectos") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { vm.showAlertDialog = false}) {
                    Text("Ok")
                }
            }
        )

    }
}