package com.example.productreviewapp.domain.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Review (
    @SerializedName("_id")
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Date, //TODO: Cambiar a tipo Fecha
    val product: Product,
    val reviewer: Reviewer,
    val comments: List<Comment>
)