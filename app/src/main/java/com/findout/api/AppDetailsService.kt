package com.findout.api

import com.findout.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppDetailsService {

    @POST("/users/appversion")
    suspend fun updateAppDetails(@Body appDetailsModel: AppDetailsModel?): Response<AppDetailsModelResponse>

    @POST("/api/v1/addUser")
    suspend fun addUser(@Body userModel:UseModel?): Response<AddUserModelResponse>

    @POST("/loginWithOtp")
    suspend fun loginWithOtp(@Body loginOtpModel:LoginOtpModel?): Response<LoginOtpDataModelResponse>

    @POST("/verifyOtp")
    suspend fun verifyOtp(@Body verifyOtpModel:VerifyOtpModel?): Response<VerifyOtpModelResponse>
}