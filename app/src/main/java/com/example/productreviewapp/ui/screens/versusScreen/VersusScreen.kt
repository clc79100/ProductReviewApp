package com.example.productreviewapp.ui.screens.versusScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.productreviewapp.domain.models.Product
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.versusScreen.versusComponents.VersusBar
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
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                    .weight(1f)
                    .fillMaxSize()
                ) {
                    AsyncImage(
                        model = vm.firstProduct?.image,
                        contentDescription = vm.firstProduct?.name,
                        modifier = Modifier.size(130.dp)
                    )
                    Text(vm.firstProduct?.name ?: "")
                }
                Box(
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                            .background(Color.LightGray)
                    )
                    Text(
                        "VS",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .size(30.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(6.dp)

                    )
                }
                Column(
                    modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                ) {
                    AsyncImage(
                        model = vm.secondProduct?.image,
                        contentDescription = vm.secondProduct?.name,
                        modifier = Modifier.size(130.dp)
                    )
                    Text(
                        text = vm.secondProduct?.name ?: "",
                        maxLines = 2
                    )
                }
            }

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

@Composable
fun VersusSpecs(
    product1: Product,
    product2: Product
) {
    val specs1 = product1.specs.flatMap { it.entries }.associate { it.key to it.value }
    val specs2 = product2.specs.flatMap { it.entries }.associate { it.key to it.value }

    val allKeys = specs1.keys

    Column(modifier = Modifier.fillMaxWidth()) {
        allKeys.forEach { key ->
            val value1 = specs1[key]
            val value2 = specs2[key]

            Spacer(Modifier.height(16.dp))
            Text(
                key.replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(8.dp))

            if (value1 is Number && value2 is Number){
                VersusBar(value1, value2)
            } else{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,) {
                    SpecProductRow(
                        value = value1,
                        modifier = Modifier.weight(1f)
                    )

                    SpecProductRow(
                        value = value2,
                        modifier = Modifier.weight(1f)
                    )
                }

            }


        }
    }
}


@Composable
fun SpecProductRow(
    value: Any?,
    modifier: Modifier
) {
    Column (
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (value is Boolean){
                Text(if (value) "Si✅" else "No❌")
            } else {
                Text(value.toString())
            }

        }
    }
}


