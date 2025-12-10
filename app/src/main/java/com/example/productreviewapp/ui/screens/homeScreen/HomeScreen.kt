package com.example.productreviewapp.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.ReviewScreenRoute
import com.example.productreviewapp.ui.screens.homeScreen.components.ReviewCardVertical
import com.example.productreviewapp.ui.screens.homeScreen.components.RowReviews
import com.example.productreviewapp.ui.screens.homeScreen.components.Search
import com.example.productreviewapp.ui.screens.homeScreen.components.SectionTitle
import com.example.productreviewapp.ui.theme.background
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
            .background(background)
            .padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
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