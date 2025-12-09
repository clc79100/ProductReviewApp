package com.example.productreviewapp.ui.screens.accountScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.AccountScreenRoute
import com.example.productreviewapp.ui.screens.EditAccountScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute
import com.example.productreviewapp.ui.viewmodels.UserViewModel

@Composable
fun AccountScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val vm: UserViewModel = viewModel()

    LaunchedEffect(Unit) { vm.loadUser() }

    LaunchedEffect(vm.isAccountDeleted) {
        if (vm.isAccountDeleted) {
            navController.navigate(LoginScreenRoute) {
                popUpTo(AccountScreenRoute) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.size(160.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            if (!vm.userProfile?.profilePhoto.isNullOrEmpty()) {
                AsyncImage(
                    model = vm.userProfile?.profilePhoto,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFF6C63FF))
                    .padding(10.dp)
                    .clickable { navController.navigate(EditAccountScreenRoute) }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp)
        ) {
            Text(
                "Nombre: ${vm.userProfile?.name ?: "Sin nombre"}",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                "Correo: ${vm.userProfile?.email ?: "Sin correo"}",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate(EditAccountScreenRoute) },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modificar Cuenta", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // BOTÓN BORRAR
        Button(
            onClick = { vm.deleteAccount() },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Borrar Cuenta", color = Color.White)
        }
    }

    if (vm.loading) {
        CustomLoading()
    }
}