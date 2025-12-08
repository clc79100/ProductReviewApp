package com.example.productreviewapp.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.productreviewapp.domain.models.Review
import com.example.productreviewapp.domain.utils.SharedPref
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.LoginScreenRoute
import com.example.productreviewapp.ui.screens.ReviewScreenRoute
import com.example.productreviewapp.ui.theme.ProductReviewAppTheme
import com.example.productreviewapp.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val vm: HomeViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .padding(paddingValues)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Button(
                        onClick = {
                            SharedPref.clear()
                            navController.navigate(LoginScreenRoute) {
                                popUpTo(HomeScreenRoute) { inclusive = true }
                            }
                        },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Cerrar Sesión")
                    }
                }
            }

            item {
                SearchSection()
            }

            if (vm.reviewsByCategory.isNotEmpty()) {
                item { SectionTitle("Por categoría IEM") }
                item { HorizontalCarousel(vm.reviewsByCategory, navController) }
            }

            if (vm.reviewsByReviewer.isNotEmpty()) {
                item { SectionTitle("Hechas por Reviewer 2") }
                item { HorizontalCarousel(vm.reviewsByReviewer, navController) }
            }

            if (vm.mainReviews.isNotEmpty()) {
                item { SectionTitle("Todas las Reviews") }

                items(vm.mainReviews) { review ->
                    ReviewCardVertical(
                        title = review.title,
                        imageUrl = review.product.image,
                        price = review.product.price,
                        onClick = { navController.navigate(ReviewScreenRoute(review.id)) }
                    )
                }
            }
        }

        if (vm.loading) {
            CustomLoading()
        }
    }
}

@Composable
fun SearchSection() {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar reviews…") },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .clip(RoundedCornerShape(25.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
    )
}

@Composable
fun HorizontalCarousel(
    reviews: List<Review>,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 20.dp, bottom = 12.dp)
    ) {
        reviews.forEach { review ->
            ReviewCardHorizontal(
                title = review.title,
                imageUrl = review.product.image,
                price = review.product.price,
                onClick = { navController.navigate(ReviewScreenRoute(review.id)) }
            )
            Spacer(modifier = Modifier.width(14.dp))
        }
    }
}

@Composable
fun ReviewCardHorizontal(title: String, imageUrl: String, price: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(220.dp)
            .shadow(8.dp, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .clickable { onClick() }
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        )

        Column(modifier = Modifier.padding(12.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Precio: $$price", fontSize = 13.sp)
        }
    }
}

@Composable
fun ReviewCardVertical(title: String, imageUrl: String, price: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
            .clickable { onClick() }
    ) {

        Column {

            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            )

            Column(modifier = Modifier.padding(15.dp)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Precio: $$price", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
        }
    }
}