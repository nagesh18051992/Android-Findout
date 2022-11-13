package com.findout.repository

import com.findout.api.AppDetailsService
import com.findout.models.AddUserModelResponse
import com.findout.models.AppDetailsModel
import com.findout.models.AppDetailsModelResponse
import com.findout.models.UseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDetailRepository @Inject constructor(private val service: AppDetailsService) {

    suspend fun fetchAppUpdate(appDetailsModel: AppDetailsModel?): Response<AppDetailsModelResponse> = withContext(
        Dispatchers.IO) {
        val appUpdate =service.updateAppDetails(appDetailsModel)
        appUpdate
    }

    suspend fun fetchLoginWithOtp(userModel: UseModel?): Response<AppDetailsModelResponse> = withContext(
        Dispatchers.IO) {
        val loginWithOtp =service.loginWithOtp(userModel)
        loginWithOtp
    }

    suspend fun fetchCreateUser(userModel: UseModel?): Response<AddUserModelResponse> = withContext(
        Dispatchers.IO) {
        val addUser =service.addUser(userModel)
        addUser
    }

    suspend fun fetchVerifyOtp(userModel: UseModel?): Response<AppDetailsModelResponse> = withContext(
        Dispatchers.IO) {
        val verifyOtp =service.verifyOtp(userModel)
        verifyOtp
    }
}