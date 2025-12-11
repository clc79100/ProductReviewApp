package com.example.productreviewapp.ui.screens.versusScreen.versusComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import coil3.compose.AsyncImage
import com.example.productreviewapp.ui.viewmodels.VersusViewModel

@Composable
fun ShowProducts(
    vm: VersusViewModel
){
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
}