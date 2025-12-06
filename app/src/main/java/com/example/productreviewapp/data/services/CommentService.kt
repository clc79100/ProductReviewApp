package com.example.productreviewapp.data.services

import com.example.productreviewapp.domain.dtos.CommentDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CommentService {
    @POST("comments")
    suspend fun createComment(
        @Header("Authorization") auth: String,
        @Body commentDTO: CommentDTO
    ): Response<Unit>

    @PUT("comments/{id}")
    suspend fun updateComment(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body commentDTO: CommentDTO
    ): Response<Unit>

    @DELETE("comments/{id}")
    suspend fun deleteComment(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    ): Response<Unit>
}