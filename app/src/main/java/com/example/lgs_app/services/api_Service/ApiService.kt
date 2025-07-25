package com.example.lgs_app.services.api_Service

import com.example.lgs_app.models.LoginRequest
import com.example.lgs_app.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
  @POST("auth/login")
  suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}