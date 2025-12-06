package com.example.productreviewapp.domain.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String,
    val name: String,
    @SerializedName("profile_photo")
    val profilePhoto: String?
)