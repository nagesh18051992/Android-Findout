package com.findout.repository

import com.findout.api.AppDetailsService
import com.findout.models.*
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

    suspend fun fetchLoginWithOtp(loginOtpModel: LoginOtpModel?): Response<LoginOtpDataModelResponse> = withContext(
        Dispatchers.IO) {
        val loginWithOtp =service.loginWithOtp(loginOtpModel)
        loginWithOtp
    }

    suspend fun fetchCreateUser(userModel: UseModel?): Response<AddUserModelResponse> = withContext(
        Dispatchers.IO) {
        val addUser =service.addUser(userModel)
        addUser
    }

    suspend fun fetchVerifyOtp(verifyOtpModel:VerifyOtpModel?): Response<VerifyOtpModelResponse> = withContext(
        Dispatchers.IO) {
        val verifyOtp =service.verifyOtp(verifyOtpModel)
        verifyOtp
    }
}