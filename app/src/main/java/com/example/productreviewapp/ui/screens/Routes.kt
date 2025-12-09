package com.example.productreviewapp.ui.screens

import kotlinx.serialization.Serializable

@Serializable
object LoginScreenRoute

@Serializable
object RegisterScreenRoute

@Serializable
object HomeScreenRoute

@Serializable
object SelectProductScreenRoute

@Serializable
data class VersusScreenRoute(val firstProductId: String, val secondProductId: String)

@Serializable
object AccountScreenRoute

@Serializable
object EditAccountScreenRoute

@Serializable
data class ReviewScreenRoute(val reviewId: String)