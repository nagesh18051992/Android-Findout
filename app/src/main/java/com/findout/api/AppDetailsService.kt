package com.findout.api

import com.findout.models.AppDetailsModel
import com.findout.models.AppDetailsModelResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppDetailsService {

    @POST("/users/appversion")
    suspend fun updateAppDetails(@Body appDetailsModel: AppDetailsModel?): Response<AppDetailsModelResponse>
}