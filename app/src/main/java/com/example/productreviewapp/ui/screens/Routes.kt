package com.example.productreviewapp.ui.screens

import kotlinx.serialization.Serializable

@Serializable
object LoginScreenRoute

@Serializable
object RegisterScreenRoute

@Serializable
object HomeScreenRoute

@Serializable
object VersusScreenRoute

@Serializable
object AccountScreenRoute

@Serializable
data class ReviewScreenRoute(val reviewId: String)
