package com.example.productreviewapp.ui.screens.versusScreen.versusComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productreviewapp.domain.models.Product

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