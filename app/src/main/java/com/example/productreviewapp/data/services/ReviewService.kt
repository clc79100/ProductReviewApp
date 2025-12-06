package com.example.productreviewapp.data.services

import com.example.productreviewapp.domain.models.Review
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {
    @GET("reviews")
    suspend fun getAllReviews() : List<Review>

    @GET("reviews/{id}")
    suspend fun getReviewById(@Path("id") id: String) : Review

    @GET("reviews")
    suspend fun getAllReviewsByCategory(
        @Query("category") category: String
    ): List<Review>

    @GET("reviews")
    suspend fun getAllReviewsByReviewer(
        @Query("reviewer") reviewer: String
    ): List<Review>
}