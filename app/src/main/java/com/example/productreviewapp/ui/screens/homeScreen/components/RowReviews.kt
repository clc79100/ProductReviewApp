package com.example.productreviewapp.ui.screens.homeScreen.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productreviewapp.domain.models.Review
import com.example.productreviewapp.ui.screens.ReviewScreenRoute
import kotlin.collections.forEach

@Composable
fun RowReviews(
    title: String,
    reviews: List<Review>,
    navController: NavController
) {
    SectionTitle(title)

    Row( // TODO: PASAR A LAZYROW
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 20.dp, bottom = 12.dp)
    ) {
        reviews.forEach { review ->
            ReviewCardHorizontal(
                title = review.title,
                imageUrl = review.product.image,
                price = review.product.price,
                onClick = {
                    navController.navigate(ReviewScreenRoute(review.id))
                }
            )
            Spacer(modifier = Modifier.width(14.dp))
        }
    }
}