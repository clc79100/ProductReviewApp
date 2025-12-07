package com.example.productreviewapp.ui.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import androidx.navigation.compose.rememberNavController
import com.example.productreviewapp.domain.utils.SharedPref
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute
import com.example.productreviewapp.ui.theme.ProductReviewAppTheme
import com.example.productreviewapp.ui.viewmodels.HomeViewModel
import kotlin.text.clear


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController
){
    val vm : HomeViewModel = viewModel ()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Button(
            onClick = {
                SharedPref.clear()
                navController.navigate(LoginScreenRoute){
                    popUpTo(HomeScreenRoute){
                        inclusive = true
                    }
                }
            }
        ) {
            Text("Cerrar Sesion")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            item {
                Text(
                    text = "Todas las Reviews",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(vm.mainReviews){ review ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFFFFFFFF))
                        .padding(8.dp)
                        .clickable {
                            //Screen de review
                        }
                ) {
                    Text(review.title)
                    Text("$ ${review.product.price}")
                }
            }

            item {
                Text(
                    text = "Por categoría IEM",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            items(vm.reviewsByCategory){ review ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFFFFFFFF))
                        .padding(8.dp)
                        .clickable {
                            //Screen de review
                        }
                ) {
                    Text(review.title)
                    Text("$ ${review.product.price}")
                }
            }

            item {
                Text(
                    text = "Hechas por Reviewer 2",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(vm.reviewsByReviewer){ review ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFFFFFFFF))
                        .padding(8.dp)
                        .clickable {
                            //Screen de review
                        }
                ) {
                    Text(review.title)
                    Text("$ ${review.product.price}")
                }
            }
        }
    }
    if (vm.loading){
        CustomLoading()
    }
}

@Preview(
    showSystemUi = true
)

@Composable
fun HomeScreenPreview(){
    ProductReviewAppTheme() {
        HomeScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}