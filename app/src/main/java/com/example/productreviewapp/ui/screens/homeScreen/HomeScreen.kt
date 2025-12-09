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
import com.example.productreviewapp.ui.screens.homeScreen.components.ButtonLogout
import com.example.productreviewapp.ui.screens.homeScreen.components.ReviewCardVertical
import com.example.productreviewapp.ui.screens.homeScreen.components.RowReviews
import com.example.productreviewapp.ui.screens.homeScreen.components.Search
import com.example.productreviewapp.ui.screens.homeScreen.components.SectionTitle
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
                //TODO: ESTO SE QUITA PORQUE VA NOMAS EN EL ACCOUNT
                ButtonLogout(navController)
            }

            item {
                Search()
            }

            item {
                RowReviews(
                    "Por categoría IEM",
                    vm.reviewsByCategory,
                    navController
                )
            }

            item {
                RowReviews(
                    "Hechas por Reviewer 2",
                    vm.reviewsByReviewer,
                    navController
                )
            }

            item {
                SectionTitle("Todas las Reviews")
            }
            items(vm.mainReviews) { review ->
                ReviewCardVertical(
                    title = review.title,
                    imageUrl = review.product.image,
                    price = review.product.price,
                    onClick = {
                        navController.navigate(ReviewScreenRoute(review.id))
                    }
                )
            }
        }

        if (vm.loading) {
            CustomLoading()
        }
    }
}