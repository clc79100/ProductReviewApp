package com.example.productreviewapp.ui.screens.accountScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.domain.utils.SharedPref
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.AccountScreenRoute
import com.example.productreviewapp.ui.screens.EditAccountScreenRoute
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute
import com.example.productreviewapp.ui.screens.accountScreen.componets.AccountButton
import com.example.productreviewapp.ui.screens.accountScreen.componets.PhotoProfile
import com.example.productreviewapp.ui.screens.accountScreen.componets.SelectPhotoSection
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
    LaunchedEffect(vm.userProfile == null) {
        vm.loadUser()
    }

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
        PhotoProfile(vm.profilePhoto){
            vm.showSheet = true
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                vm.userProfile?.name ?: "Sin nombre",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Text(
                vm.userProfile?.email ?: "Sin correo",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }

        AccountButton(
            text = "Modificar Cuenta",
            color = Color(0xFF6C63FF),
            modifier = Modifier.padding(horizontal = 100.dp),
            onClick = {navController.navigate(EditAccountScreenRoute)}
        )
        Text(
            text = "Otras Opciones",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
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
            onClick = {vm.deleteAccount()}
        )
    }

    if (vm.showSheet){
        ModalBottomSheet(
            onDismissRequest = {vm.showSheet = false},
            dragHandle = { BottomSheetDefaults.DragHandle() },
            sheetState = sheetState
        ) {
            SelectPhotoSection(
                vm.imageList
            ) { image ->
                vm.profilePhoto = image
                vm.updateProfilePhoto()
                vm.showSheet = false
            }
        }
    }

    if (vm.loading) {
        CustomLoading()
    }
}