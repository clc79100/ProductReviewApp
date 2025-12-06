package com.example.productreviewapp.data.services

import com.example.productreviewapp.domain.dtos.AuthResponse
import com.example.productreviewapp.domain.dtos.Login
import com.example.productreviewapp.domain.dtos.UserDTO
import com.example.productreviewapp.domain.models.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthService {

    //@POST("users") //TODO: cambiar a "auth/register/"
    //suspend fun register(@Body register: Register) : Unit //TODO: cambiar lo que retorna

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): UserProfile

    @POST("users")
    suspend fun register(
        @Body userDTO: UserDTO
    ): Response<Unit>

    @PUT("users/{id}")
    suspend fun updateUser(
        @Header ("Authorization") auth: String,
        @Path("id") id: String,
        @Body userDTO: UserDTO
    ): Response<Unit>

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Header ("Authorization") auth: String,
        @Path ("id") id: String
    ): Response<Unit>

    @POST("auth/login")
    suspend fun login(@Body login: Login) : AuthResponse
}