package com.example.productreviewapp.ui.screens.homeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productreviewapp.domain.models.Review
import com.example.productreviewapp.ui.screens.ReviewScreenRoute

@Composable
fun RowReviews(
    title: String,
    reviews: List<Review>,
    navController: NavController
) {
    SectionTitle(title)

    LazyRow(
        modifier = Modifier
            .padding(bottom = 12.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)

    ) {
        items(reviews) { review ->
            ReviewCardHorizontal(
                title = review.title,
                imageUrl = review.product.image,
                price = review.product.price,
                reviewer = review.reviewer,
                onClick = {
                    navController.navigate(ReviewScreenRoute(review.id))
                }
            )
        }
    }
}