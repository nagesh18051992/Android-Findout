package com.findout.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findout.models.AppDetailsModelResponse
import com.findout.utils.NetworkResult
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    val appUpdate: MutableLiveData<NetworkResult<AppDetailsModelResponse?>> = MutableLiveData()

    fun init() {

    }
}