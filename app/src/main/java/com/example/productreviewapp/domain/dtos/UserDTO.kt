package com.example.productreviewapp.domain.dtos

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val name: String,
    val email: String,
    val password: String,
    @SerializedName("profile_photo")
    val profilePhoto: String?
)