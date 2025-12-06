package com.example.productreviewapp.data.services

import com.example.productreviewapp.domain.models.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("products")
    suspend fun getAllProducts() : List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id")id: String): Product
}