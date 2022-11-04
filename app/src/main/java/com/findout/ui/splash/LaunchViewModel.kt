package com.findout.ui.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findout.BuildConfig
import com.findout.models.AppDetailsModel
import com.findout.repository.AppDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.findout.models.AppDetailsModelResponse
import com.findout.utils.NetworkResult
import com.findout.utils.hasInternetConnection
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
class LaunchViewModel @Inject constructor(private val repository: AppDetailRepository,private val application: Application) : ViewModel() {

    val appUpdate: MutableLiveData<NetworkResult<AppDetailsModelResponse?>> = MutableLiveData()

    fun init() {
       fetchAppUpdate(getAppDetails())
    }

    private fun fetchAppUpdate(appDetailsModel: AppDetailsModel?){
        appUpdate.postValue(NetworkResult.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(application.applicationContext)) {
                    val response = repository.fetchAppUpdate(appDetailsModel)
                    appUpdate.postValue(NetworkResult.Success(response.body()))
                } else
                    appUpdate.postValue(NetworkResult.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> appUpdate.postValue(NetworkResult.Error("Network Failure " +  ex.localizedMessage))
                    else -> appUpdate.postValue(NetworkResult.Error("Conversion Error"))
                }
            }
        }
    }

    private fun getAppDetails(): AppDetailsModel {
        val appDetailsModel = AppDetailsModel()
        appDetailsModel.apply {
            appVersion = BuildConfig.VERSION_CODE.toInt()
        }
        return appDetailsModel
    }
}