package com.example.productreviewapp.domain.models

import com.google.gson.annotations.SerializedName

data class Reviewer (
    @SerializedName("_id")
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val profile_photo: String
)