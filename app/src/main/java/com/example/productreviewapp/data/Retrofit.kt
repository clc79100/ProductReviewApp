package com.example.productreviewapp.data

import com.example.productreviewapp.data.services.AuthService
import com.example.productreviewapp.data.services.CommentService
import com.example.productreviewapp.data.services.ProductService
import com.example.productreviewapp.data.services.ReviewService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val base_url = "https://oreja-negra.negroides.world/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createAuthService(): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    fun createProductService(): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    fun createReviewService(): ReviewService {
        return retrofit.create(ReviewService::class.java)
    }

    fun createCommentService(): CommentService {
        return  retrofit.create(CommentService::class.java)
    }
}