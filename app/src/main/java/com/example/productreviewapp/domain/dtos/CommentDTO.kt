package com.example.productreviewapp.domain.dtos

import com.google.gson.annotations.SerializedName

data class CommentDTO(
    val content: String,
    @SerializedName("review_id")
    val reviewId: String? = null
)
