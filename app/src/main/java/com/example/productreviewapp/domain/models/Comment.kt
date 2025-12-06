package com.example.productreviewapp.domain.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Comment(
    @SerializedName("_id")
    val id : String,
    val content: String,
    val createdAt: Date, //TODO: cambiar a tipo Fecha
    @SerializedName("user_id") //TODO: remover @SerializedName
    val user: User?
)