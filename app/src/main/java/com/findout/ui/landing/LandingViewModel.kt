package com.findout.ui.landing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findout.models.AppDetailsModelResponse
import com.findout.utils.NetworkResult
import javax.inject.Inject

class LandingViewModel @Inject constructor() : ViewModel() {

    val appUpdate: MutableLiveData<NetworkResult<AppDetailsModelResponse?>> = MutableLiveData()

    fun init(){

    }
}