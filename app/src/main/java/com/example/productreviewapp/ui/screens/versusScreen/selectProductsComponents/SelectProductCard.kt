package com.example.productreviewapp.ui.screens.versusScreen.selectProductsComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.productreviewapp.domain.models.Product
import com.example.productreviewapp.ui.theme.ProductReviewAppTheme

@Composable
fun SelectProductCard(product: Product,onClick: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(3.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable{onClick()}
            .background(Color.White)
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .dropShadow(
                    shape = RoundedCornerShape(20.dp),
                    shadow = Shadow(
                        radius = 56.dp,
                        spread = 4.dp,
                        color = Color(0x20000000),
                        offset = DpOffset(x = 6.dp, 6.dp)
                    )
                )
        ){
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier
                    .size(150.dp)
            )
        }
        Text(
            text = product.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun ProductPreview(){
    val product = Product(
        "sdasd",
        "Product Name",
        "dasdasd",
        "dsadas",
        "dsadas",
        111,
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765309450/Lethuoer_s12_Pro_krhjkv.png",
        emptyList()
    )
    ProductReviewAppTheme {
        SelectProductCard(product,{})
    }
}