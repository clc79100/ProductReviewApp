package com.example.productreviewapp.ui.screens.versusScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.domain.models.Product
import com.example.productreviewapp.ui.components.CustomCircularLoading
import com.example.productreviewapp.ui.screens.VersusScreenRoute
import com.example.productreviewapp.ui.screens.versusScreen.selectProductsComponents.ProductCard
import com.example.productreviewapp.ui.screens.versusScreen.selectProductsComponents.SelectProductButton
import com.example.productreviewapp.ui.screens.versusScreen.selectProductsComponents.SelectProductCard
import com.example.productreviewapp.ui.screens.versusScreen.selectProductsComponents.SelectProductSection
import com.example.productreviewapp.ui.theme.BlueGradient
import com.example.productreviewapp.ui.theme.RedGradient
import com.example.productreviewapp.ui.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectProductScreen(
    paddingValues: PaddingValues,
    navController: NavController
){
    val vm : ProductViewModel = viewModel()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IsProductSelected(
                product = vm.firstProduct,
                buttonText = "Selecciona un producto",
                isEnabled = true,
                gradientColors = BlueGradient,
                modifier = Modifier.weight(1f),
                onClickSelect = {
                    vm.isFirstButtonClicked = true
                    vm.showSheet = true
                    vm.loadProducts()
                },
                onClickDelete = {
                    vm.firstProduct = null
                    vm.isEnabled = false
                }
            )
            Spacer(Modifier.weight(0.1f))

            IsProductSelected(
                product = vm.secondProduct,
                buttonText =  "Selecciona otro producto",
                isEnabled = true,
                gradientColors = RedGradient,
                modifier = Modifier.weight(1f),
                onClickSelect = {
                    vm.isSecondButtonClicked = true
                    vm.showSheet = true
                    vm.loadProducts()
                },
                onClickDelete = {
                    vm.secondProduct = null
                    vm.isEnabled = false
                })

            Spacer(Modifier.weight(0.1f))

            Button(
                enabled = vm.isEnabled,
                onClick = {
                    navController.navigate(
                        VersusScreenRoute(
                            vm.firstProduct!!.id,
                            vm.secondProduct!!.id
                        )
                    )
                }
            ) {
                Text("Comparar")
            }
        }
    }

    if(vm.showSheet){
        ModalBottomSheet(
            onDismissRequest = {
                vm.isSecondButtonClicked = false
                vm.isFirstButtonClicked = false
                vm.showSheet = false
                               },
            dragHandle = { BottomSheetDefaults.DragHandle() },
            sheetState = sheetState
        ) {
            vm.filterListOnNextProduct()
            SelectProductSection(vm.products) { product ->
                if(vm.isFirstButtonClicked){
                    vm.firstProduct = product
                    vm.isFirstButtonClicked = false
                }

                if (vm.isSecondButtonClicked){
                    vm.secondProduct = product
                    vm.isSecondButtonClicked = false
                }

                if (vm.firstProduct != null && vm.secondProduct != null) {
                    vm.isEnabled = true
                }
                vm.actualCategory = product.category
                vm.showSheet = false
            }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(vm.products) { product ->
                        SelectProductCard(product) {}
                    }
                }
                if (vm.loading){
                    CustomCircularLoading()
                }

        }
    }
}


@Composable
fun IsProductSelected(
    product: Product?,
    buttonText: String,
    isEnabled: Boolean,
    gradientColors: List<Color>,
    modifier: Modifier,
    onClickSelect: () -> Unit,
    onClickDelete: ()-> Unit
){
    if(product != null){
        ProductCard(
            product,
            gradientColors,
            modifier
        ) {
            onClickDelete()
        }
    } else{
        SelectProductButton(buttonText, isEnabled, modifier) {
            onClickSelect()
        }
    }
}