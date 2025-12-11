package com.example.productreviewapp.ui.screens.versusScreen.versusComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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