package com.findout.ui.otp

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findout.models.AppDetailsModelResponse
import com.findout.models.UseModel
import com.findout.models.VerifyOtpModel
import com.findout.models.VerifyOtpModelResponse
import com.findout.repository.AppDetailRepository
import com.findout.utils.NetworkResult
import com.findout.utils.hasInternetConnection
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class VerifyOtpViewModel @Inject constructor(private val repository: AppDetailRepository, private val application: Application) : ViewModel() {

    val appUpdate: MutableLiveData<NetworkResult<VerifyOtpModelResponse?>> = MutableLiveData()

    fun init(){

    }

    fun fetchVerifyOtp(verifyOtpModel: VerifyOtpModel?){
        appUpdate.postValue(NetworkResult.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(application.applicationContext)) {
                    val response = repository.fetchVerifyOtp(verifyOtpModel)
                    appUpdate.postValue(NetworkResult.Success(response.body()))
                } else
                    appUpdate.postValue(NetworkResult.Error("No Internet Connection"))
            } catch (ex: Exception){
                when (ex) {
                    is IOException -> appUpdate.postValue(NetworkResult.Error("Network Failure " +  ex.localizedMessage))
                    else -> appUpdate.postValue(NetworkResult.Error("Conversion Error"))
                }
            }
        }
    }

}