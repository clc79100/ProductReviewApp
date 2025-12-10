package com.example.productreviewapp.domain.dtos

import com.google.gson.annotations.SerializedName

data class PhotoProfile(
    @SerializedName("profile_photo")
    val profilePhoto: String?
)
