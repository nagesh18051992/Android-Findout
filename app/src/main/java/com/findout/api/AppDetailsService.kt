package com.findout.api

import com.findout.models.AppDetailsModel
import com.findout.models.AppDetailsModelResponse
import com.findout.models.UseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppDetailsService {

    @POST("/users/appversion")
    suspend fun updateAppDetails(@Body appDetailsModel: AppDetailsModel?): Response<AppDetailsModelResponse>

    @POST("/addUser")
    suspend fun addUser(@Body userModel:UseModel?): Response<AppDetailsModelResponse>

    @POST("/loginWithOtp")
    suspend fun loginWithOtp(@Body userModel:UseModel?): Response<AppDetailsModelResponse>

    @POST("/verifyOtp")
    suspend fun verifyOtp(@Body userModel:UseModel?): Response<AppDetailsModelResponse>
}