package com.example.productreviewapp.domain.dtos

data class AuthResponse(
    val token: String,
    val user: AuthUser
)