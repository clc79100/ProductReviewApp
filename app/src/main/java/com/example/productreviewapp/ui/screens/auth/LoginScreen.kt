package com.example.productreviewapp.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute
import com.example.productreviewapp.ui.screens.RegisterScreenRoute
import com.example.productreviewapp.ui.screens.auth.components.AuthHeader
import com.example.productreviewapp.ui.screens.auth.components.AuthPrompt
import com.example.productreviewapp.ui.screens.auth.components.CustomButton
import com.example.productreviewapp.ui.theme.BlueGradient
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
                .fillMaxSize()
                .background(Brush.horizontalGradient(BlueGradient.reversed()))
                .padding(top = 20.dp),
        ) {
            AuthHeader(
                title1 = "¡Bienvenido!",
                title2 = "Inicia Sesión",
                Modifier.weight(0.5f)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .shadow(16.dp, shape = RoundedCornerShape( topEnd = 80.dp))
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
                        value = vm.email,
                        onValueChange = { vm.email = it },
                        label = {Text("Correo")},
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = vm.password,
                        onValueChange = {vm.password = it},
                        label = {Text("Contraseña")},
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )
                }

                CustomButton(Modifier.weight(1f)){
                    vm.login()
                    focusManager.clearFocus()
                }

                AuthPrompt(navController) {
                    navController.navigate(RegisterScreenRoute)
                }
            }
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