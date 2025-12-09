package com.example.productreviewapp.ui.screens.versusScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.domain.models.Product
import com.example.productreviewapp.ui.components.CustomLoading
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
            Text("VersusScreen")

            Text(vm.firstProduct?.name ?: "")
            Text(vm.secondProduct?.name ?: "")

            Spacer(Modifier.height(16.dp))

            if (vm.firstProduct != null && vm.secondProduct != null) {

                SpecsComparison(
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
fun SpecsComparison(
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

            val maxValue: Float? =
                if (value1 is Number && value2 is Number)
                    maxOf(value1.toFloat(), value2.toFloat())
                else null

            SpecProductRow(
                productName = product1.name,
                value = value1,
                maxValue = maxValue
            )

            Spacer(Modifier.height(10.dp))

            SpecProductRow(
                productName = product2.name,
                value = value2,
                maxValue = maxValue
            )
        }
    }
}


@Composable
fun SpecProductRow(
    productName: String,
    value: Any?,
    maxValue: Float?
) {
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                productName,
                fontWeight = FontWeight.SemiBold
            )
            Text(value.toString())
        }

        if (value is Number && maxValue != null && maxValue > 0f) {

            val progress = (value.toFloat() / maxValue).coerceIn(0f, 1f)

            LinearProgressIndicator(
                progress = { progress },
                color = ProgressIndicatorDefaults.linearColor,
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }
    }
}