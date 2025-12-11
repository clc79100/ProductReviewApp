package com.example.productreviewapp.ui.screens.versusScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.versusScreen.versusComponents.ShowProducts
import com.example.productreviewapp.ui.screens.versusScreen.versusComponents.VersusSpecs
import com.example.productreviewapp.ui.viewmodels.VersusViewModel

@Composable
fun VersusScreen(
    paddingValues: PaddingValues,
    navController: NavController
){
    val vm: VersusViewModel = viewModel()

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            ShowProducts(
                vm = vm
            )

            Spacer(Modifier.height(16.dp))

            if (vm.firstProduct != null && vm.secondProduct != null) {
                VersusSpecs(
                    product1 = vm.firstProduct!!,
                    product2 = vm.secondProduct!!
                )
            }
        }
    }

    if (vm.loading){
        CustomLoading()
    }
}