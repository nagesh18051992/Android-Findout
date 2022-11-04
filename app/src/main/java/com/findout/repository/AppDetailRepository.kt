package com.findout.repository

import com.findout.api.AppDetailsService
import com.findout.models.AppDetailsModel
import com.findout.models.AppDetailsModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDetailRepository @Inject constructor(private val service: AppDetailsService) {

    suspend fun fetchAppUpdate(appDetailsModel: AppDetailsModel?): Response<AppDetailsModelResponse> = withContext(
        Dispatchers.IO) {
        val popular =service.updateAppDetails(appDetailsModel)
        popular
    }
}