package com.example.productreviewapp.ui.screens.accountScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.productreviewapp.domain.utils.SharedPref
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.AccountScreenRoute
import com.example.productreviewapp.ui.screens.EditAccountScreenRoute
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute
import com.example.productreviewapp.ui.screens.accountScreen.componets.AccountButton
import com.example.productreviewapp.ui.screens.accountScreen.componets.PhotoProfile
import com.example.productreviewapp.ui.viewmodels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val vm: UserViewModel = viewModel()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

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
        PhotoProfile(vm.userProfile?.profilePhoto){
            vm.showSheet = true
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

        AccountButton(
            text = "Modificar Cuenta",
            color = Color(0xFF6C63FF),
            onClick = {navController.navigate(EditAccountScreenRoute)}
        )

        AccountButton(
            text = "Cerrar Sesión",
            color = Color.Gray,
            onClick = {
                SharedPref.clear()
                navController.navigate(LoginScreenRoute) {
                    popUpTo(HomeScreenRoute) { inclusive = true }
                }
            }
        )

        AccountButton(
            text = "Eliminar Cuenta",
            color = Color.Red.copy(alpha = 0.8f),
            onClick = {navController.navigate(EditAccountScreenRoute)}
        )
    }

    if (vm.showSheet){
        ModalBottomSheet(
            onDismissRequest = {vm.showSheet = false},
            dragHandle = { BottomSheetDefaults.DragHandle() },
            sheetState = sheetState
        ) {
            Column(Modifier.padding(horizontal = 12.dp)){
                Text(
                    text = "Elige una Foto de Perfil",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(vm.imageList){ image ->
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }
            }

        }
    }

    if (vm.loading) {
        CustomLoading()
    }
}