package com.example.productreviewapp.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Contextual

data class Product(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val description: String,
    val brand: String,
    val category: String,
    val price: Int,
    val image: String,
    val specs: List<Map<String, @Contextual Any>>,
)