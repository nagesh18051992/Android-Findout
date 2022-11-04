package com.findout.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findout.models.AppDetailsModelResponse
import com.findout.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    val appUpdate: MutableLiveData<NetworkResult<AppDetailsModelResponse?>> = MutableLiveData()

    fun init() {

    }
}