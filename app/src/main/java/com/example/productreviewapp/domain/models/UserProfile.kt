package com.example.productreviewapp.domain.models

import com.google.gson.annotations.SerializedName

data class UserProfile (
    @SerializedName("_id")
    val id: String,
    val name: String,
    val email: String,
    @SerializedName("profile_photo")
    val profilePhoto: String?
)